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

            int numberOfYears = ((Long) jsonObject.get("numberOfYears")).intValue();
            input.setNumberOfYears(numberOfYears);

            double santaBudget = ((Long) jsonObject.get("santaBudget")).doubleValue();
            input.setSantaBudget(santaBudget);

            JSONObject initialData = (JSONObject) jsonObject.get("initialData");

            // We add the children in a list
            JSONArray listOfChildren = (JSONArray) ((JSONObject) initialData).get("children");
            List<Child> childList = InputHelper.returnChildren(listOfChildren);
            input.setChildList(childList);

            // We add the gifts in a list
            JSONArray giftList = (JSONArray) ((JSONObject) initialData).get("santaGiftsList");
            List<Gift> santaGiftList = InputHelper.returnGifts(giftList);
            input.setGiftList(santaGiftList);

            // We add the changes in a list
            List<Change> annualChanges = new ArrayList<>();
            JSONArray changes = (JSONArray) jsonObject.get("annualChanges");
            if (changes != null) {
                for (Object change : changes) {
                    int newSantaBudget = -1;
                    if (((JSONObject) change).get("newSantaBudget") != null) {
                        newSantaBudget = ((Long) ((JSONObject) change).get("newSantaBudget")).intValue();
                    }
                    // We add the new gifts in a list
                    List<Gift> newGifts = InputHelper.returnGifts((JSONArray) ((JSONObject) change).get("newGifts"));

                    // We add the new children in a list
                    List<Child> newChildren = InputHelper.returnChildren((JSONArray) ((JSONObject) change).get("newChildren"));

                    // We add the Children updates in a list
                    List<Update> childrenUpdates = new ArrayList<>();
                    JSONArray allNewUpdates = (JSONArray) ((JSONObject) change).get("childrenUpdates");
                    if (allNewUpdates != null) {
                        for (Object update : allNewUpdates) {
                            int id = -1;
                            if(((JSONObject) update).get("id") != null) {
                                id = ((Long)((JSONObject) update).get("id")).intValue();
                            }
                            double niceScore = -1;
                            if(((JSONObject) update).get("niceScore") != null) {
                                niceScore = ((Long)((JSONObject) update).get("niceScore")).doubleValue();
                            }
                            JSONArray giftCategories = (JSONArray) ((JSONObject) update).get("giftsPreferences");
                            List<String> giftsPreferences = new ArrayList<>();
                            for (Object giftCategory : giftCategories) {
                                String gift = (String) giftCategory;
                                giftsPreferences.add(gift);
                            }
                        }
                    }
                    Change newChange = new Change(newSantaBudget, newGifts, newChildren, childrenUpdates);
                    annualChanges.add(newChange);
                }
            }
            input.setAnnualChanges(annualChanges);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return input;
    }


}
