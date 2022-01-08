package main;

import checker.Checker;
import children.Child;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.budget.CalculateChildBudget;
import commands.budget.CalculateScoresSum;
import commands.changes.MakeChanges;
import commands.changes.RemoveDuplicateCategories;
import commands.gifts.strategy.GiftStrategy;
import commands.gifts.strategy.GiftStrategyFactory;
import northpole.Santa;
import northpole.elves.Elf;
import northpole.elves.ElfFactory;
import input.Input;
import input.InputJSON;
import northpole.Change;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static common.Constants.INPUT_PATH;
import static common.Constants.TESTS_NUMBER;
import static common.Constants.FILE_EXTENSION;
import static common.Constants.OUTPUT_PATH;
import static common.Constants.TEEN_AGE;
import static common.Constants.YELLOW_ELF;


/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     *
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        for (int i = 1; i <= TESTS_NUMBER; i++) {
            String inputPath = INPUT_PATH + i + FILE_EXTENSION;
            String outputPath = OUTPUT_PATH + i + FILE_EXTENSION;
            action(inputPath, outputPath);
        }
        Checker.calculateScore();
    }

    /**
     * Main function
     *
     * @param filePath1
     * @param filePath2
     * @throws IOException
     */
    public static void action(final String filePath1, final String filePath2) throws IOException {
        InputJSON inputJSON = new InputJSON(filePath1);
        FileWriter fileWriter = new FileWriter(filePath2);
        JSONObject output = new JSONObject();
        JSONArray outputAnnualChildren = new JSONArray();

        Input input = inputJSON.readData();

        for (int i = 0; i <= input.getNumberOfYears(); i++) {
            /////////////// Do not modify /////////////////
            JSONObject thisYearChildren = new JSONObject();
            ///////////////////////////////////////////////

            // If we are not in the first round, we register the changes
            if (i != 0) {
                // Increment the age by 1
                input.getChildList().forEach(a -> a.setAge(a.getAge() + 1));
                // Make the changes
                Change change = input.getAnnualChanges().get(i - 1);
                MakeChanges makeChanges = new MakeChanges(change, input, input.getChildList());
                makeChanges.execute();
            }
            // Remove young adults
            input.getChildList().removeIf(a -> a.getAge() > TEEN_AGE);
            // Remove same category preferences (duplicates)
            RemoveDuplicateCategories remDup = new RemoveDuplicateCategories(input.getChildList());
            remDup.execute();

            // Initialize Santa
            Santa santa = Santa.getInstance();

            // Add the gift list to santa
            santa.setGiftList(input.getGiftList());

            // Calculate the average score sums and also set each child's nice score
            // by calling CalculateScore function for each child
            CalculateScoresSum averageScoresSum = new CalculateScoresSum(input.getChildList());
            averageScoresSum.execute();
            santa.setAverageScoresSum(averageScoresSum.getSum());

            // Calculate the budget allocated for each child and store in Santa's HashMap.
            // Also apply changes for the elves (Black, White and Pink)
            Map<Integer, Double> childrenBudgetList = new LinkedHashMap<>();
            for (Child child : input.getChildList()) {
                CalculateChildBudget childBudget = new CalculateChildBudget(child,
                        santa.getAverageScoresSum(), input.getSantaBudget());
                childBudget.execute();

                Elf elf = ElfFactory.createElf(child, input.getGiftList(), childBudget.getBudget());
                if (!child.getElf().equals(YELLOW_ELF)) {
                    elf.execute();
                    childrenBudgetList.put(child.getId(), elf.getBudget());
                } else {
                    childrenBudgetList.put(child.getId(), childBudget.getBudget());
                }
            }
            santa.setBudgetList(childrenBudgetList);

            GiftStrategy strategy = GiftStrategyFactory.createStrategy(input.getChildList(),
                    input.getRoundStrategy());

            JSONArray outputYearlyGifted = strategy.getGiftList();

            /////////////// Do not modify /////////////////
            thisYearChildren.put("children", outputYearlyGifted);
            outputAnnualChildren.add(thisYearChildren);
            //////////////////////////////////////////////
        }

        output.put("annualChildren", outputAnnualChildren);
        ObjectMapper mapper = new ObjectMapper();
        String outputString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(output);
        fileWriter.write(outputString);
        fileWriter.close();
    }
}
