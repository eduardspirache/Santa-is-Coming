package input;

import children.Child;
import children.Update;
import enums.Category;
import northpole.Change;
import northpole.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputJSON {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputJSON(final String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * Reads data from JSON
     *
     * @return an input object with all the data
     */
    public Input readData() {
        Input input = new Input();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(this.getInputPath()));
            JSONObject jsonObject = (JSONObject) obj;

            int numberOfYears = (int) jsonObject.get("numberOfYears");
            input.setNumberOfYears(numberOfYears);

            double santaBudget = (double) jsonObject.get("santaBudget");
            input.setSantaBudget(santaBudget);

            JSONArray initialData = (JSONArray) jsonObject.get("initialData");

            // We add the children in a list
            List<Child> childList = InputHelper.returnChildren((JSONArray) initialData.get(0));
            input.setChildList(childList);

            // We add the gifts in a list
            List<Gift> santaGiftList = InputHelper.returnGifts((JSONArray) initialData.get(1));
            input.setGiftList(santaGiftList);

            // We add the changes in a list
            JSONArray changes = (JSONArray) jsonObject.get("annualChanges");
            List<Change> annualChanges = new ArrayList<>();
            for (int i = 0; i < changes.size(); i++) {
                int newSantaBudget = (int) changes.get(0);

                // We add the new gifts in a list
                List<Gift> newGifts = InputHelper.returnGifts((JSONArray) changes.get(1));

                // We add the new children in a list
                List<Child> newChildren = InputHelper.returnChildren((JSONArray) changes.get(2));

                // We add the Children updates in a list
                List<Update> childrenUpdates = new ArrayList<>();
                JSONArray allNewUpdates = (JSONArray) changes.get(3);
                for (int j = 0; j < allNewUpdates.size(); j++) {
                    int id = (int) allNewUpdates.get(0);
                    double niceScore = (double) allNewUpdates.get(1);

                    JSONArray giftCategories = (JSONArray) allNewUpdates.get(2);
                    List<String> giftsPreferences = new ArrayList<>();
                    for (int k = 0; k < giftCategories.size(); k++) {
                        String gift = (String) giftCategories.get(k);
                        giftsPreferences.add(gift);
                    }
                }
                Change change = new Change(newSantaBudget, newGifts, newChildren, childrenUpdates);
                annualChanges.add(change);
            }
            input.setAnnualChanges(annualChanges);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return input;
    }


}
