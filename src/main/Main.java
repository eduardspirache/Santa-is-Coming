package main;

import checker.Checker;
import children.Child;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.budget.CalculateChildBudget;
import commands.budget.CalculateScoresSum;
import commands.changes.MakeChanges;
import commands.elfs.Elf;
import commands.elfs.ElfFactory;
import commands.gifts.SendGifts;
import commands.score.CalculateScore;
import input.Input;
import input.InputJSON;
import northpole.Change;
import northpole.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static common.Constants.INPUT_PATH;
import static common.Constants.TESTS_NUMBER;
import static common.Constants.FILE_EXTENSION;
import static common.Constants.OUTPUT_PATH;
import static common.Constants.TEEN_AGE;


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
            JSONArray outputYearlyGifted = new JSONArray(); // we add the children here
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

            // Calculate the average score sums and also set each child's nice score
            // by calling CalculateScore function for each child
            CalculateScoresSum averageScoresSum = new CalculateScoresSum(input.getChildList());
            averageScoresSum.execute();
            double scoresSum = averageScoresSum.getSum();
            // Iterate through the children list
            for (Child child : input.getChildList()) {
                // Calculate the budget allocated for the child
                CalculateChildBudget childBudget = new CalculateChildBudget(child,
                        scoresSum, input.getSantaBudget());
                childBudget.execute();
                double assignedBudget = childBudget.getBudget();
                // Apply changes for the elves (Black, White and Pink)
                Elf elf = ElfFactory.createElf(child, input.getGiftList(), assignedBudget);
                elf.execute();
                if (!child.getElf().equals("yellow")) {
                    assignedBudget = elf.getBudget();
                }
                // Store the gifts
                SendGifts sendGifts = new SendGifts(child, assignedBudget, input.getGiftList());
                sendGifts.execute();
                List<Gift> receivedGifts = sendGifts.getReceivedGifts();

                // Apply changes for Yellow Elf if the child's gift list is empty
                // elf.getGift() is always null for other elves than Yellow
                if (receivedGifts.size() == 0 && elf.getGift() != null) {
                    receivedGifts.add(elf.getGift());
                }

                Child copyChild = new Child(child);
                JSONObject jsonChild = new JSONObject();
                jsonChild.put("id", copyChild.getId());
                jsonChild.put("lastName", copyChild.getLastName());
                jsonChild.put("firstName", copyChild.getFirstName());
                jsonChild.put("city", copyChild.getCity());
                jsonChild.put("age", copyChild.getAge());
                jsonChild.put("giftsPreferences", copyChild.getGiftsPreferences());
                jsonChild.put("averageScore", child.getNiceScore());
                jsonChild.put("niceScoreHistory", copyChild.getScoreList());
                jsonChild.put("assignedBudget", assignedBudget);
                JSONArray receivedJSONGifts = new JSONArray();
                for (Gift gift : receivedGifts) {
                    JSONObject jsonGift = new JSONObject();
                    jsonGift.put("productName", gift.getProductName());
                    jsonGift.put("price", gift.getPrice());
                    jsonGift.put("category", gift.getCategory());
                    receivedJSONGifts.add(jsonGift);
                }
                jsonChild.put("receivedGifts", receivedJSONGifts);
                outputYearlyGifted.add(jsonChild);
            }

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
