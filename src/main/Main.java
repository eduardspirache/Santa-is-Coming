package main;

import checker.Checker;
import children.Child;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.budget.CalculateChildBudget;
import commands.budget.CalculateScoresSum;
import commands.changes.MakeChanges;
import commands.gifts.SendGifts;
import commands.score.CalculateScore;
import database.Database;
import input.Input;
import input.InputJSON;
import northpole.Change;
import northpole.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;

import static common.Constants.*;

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
            String refPath = REF_PATH + i;
            action(inputPath, outputPath);
        }
        Checker.calculateScore();
    }

    public static void action(final String filePath1, final String filePath2) throws IOException {
        InputJSON inputJSON = new InputJSON(filePath1);
        FileWriter fileWriter = new FileWriter(filePath2);
        JSONObject output = new JSONObject();
        JSONArray outputAnnualChildren = new JSONArray();

        Input input = inputJSON.readData();
        Database database = new Database(input.getChildList(), input.getGiftList());

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

            // Calculate the average score sums
            CalculateScoresSum averageScoresSum = new CalculateScoresSum(input.getChildList());
            averageScoresSum.execute();
            double scoresSum = averageScoresSum.getSum();
            // Iterate through the children list
            for (Child child : input.getChildList()) {
                //Calculate the budget allocated for the child
                CalculateChildBudget childBudget = new CalculateChildBudget(child,
                        scoresSum, input.getSantaBudget());
                childBudget.execute();
                double assignedBudget = childBudget.getBudget();
                // Calculate the child's average score (nice score)
                CalculateScore calculateScore = new CalculateScore(child);
                calculateScore.execute();
                double niceScore = calculateScore.getAverageScore();
                // Store the gifts
                SendGifts sendGifts = new SendGifts(child, assignedBudget, input.getGiftList());
                sendGifts.execute();
                List<Gift> receivedGifts = sendGifts.getReceivedGifts();

                JSONObject jsonChild = new JSONObject();
                jsonChild.put("id", child.getId());
                jsonChild.put("lastName", child.getLastName());
                jsonChild.put("firstName", child.getFirstName());
                jsonChild.put("city", child.getCity());
                jsonChild.put("age", child.getAge());
                jsonChild.put("giftsPreferences", child.getGiftsPreferences());
                jsonChild.put("averageScore", niceScore);
                jsonChild.put("niceScoreHistory", child.getScoreList());
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
