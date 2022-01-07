package input;

import children.Child;
import children.Update;
import northpole.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class InputHelper {

    private InputHelper() {
        // Constructor for checkstyle
    }

    /**
     * @return children list from JSONArray
     */
    public static List<Child> returnChildren(final JSONArray children) {
        List<Child> childList = new ArrayList<>();
        if (children != null) {
            for (Object child : children) {

                int id = -1;
                if (((JSONObject) child).get("id") != null) {
                    id = ((Long) ((JSONObject) child).get("id")).intValue();
                }

                String lastName = (String) ((JSONObject) child).get("lastName");
                String firstName = (String) ((JSONObject) child).get("firstName");

                int age = -1;
                if (((JSONObject) child).get("age") != null) {
                    age = ((Long) ((JSONObject) child).get("age")).intValue();
                }

                String city = (String) ((JSONObject) child).get("city");

                double niceScore = -1;
                if (((JSONObject) child).get("niceScore") != null) {
                    niceScore = ((Long) ((JSONObject) child).get("niceScore")).doubleValue();

                }
                List<String> giftsPreferences = new ArrayList<>();
                JSONArray giftsFromJSON = (JSONArray) ((JSONObject) child).get("giftsPreferences");
                if (giftsFromJSON != null) {
                    for (Object o : giftsFromJSON) {
                        String newCategory = (String) o;
                        giftsPreferences.add(newCategory);
                    }
                }
                int niceScoreBonus = -1;
                if (((JSONObject) child).get("niceScoreBonus") != null) {
                    niceScoreBonus = ((Long) ((JSONObject) child).get("niceScoreBonus")).intValue();
                }

                String elf = ((String) ((JSONObject) child).get("elf"));

                Child newChild = new Child(id, lastName, firstName, age,
                        city, niceScore, giftsPreferences, niceScoreBonus, elf);
                childList.add(newChild);
            }
        }
        return childList;
    }

    /**
     * @return gifts list from JSONArray
     */
    public static List<Gift> returnGifts(final JSONArray gifts) {
        List<Gift> santaGiftList = new ArrayList<>();
        if (gifts != null) {
            for (Object gift : gifts) {
                String productName = (String) ((JSONObject) gift).get("productName");

                int price = -1;
                if (((JSONObject) gift).get("price") != null) {
                    price = (int) ((Long) ((JSONObject) gift).get("price")).intValue();
                }

                String category = (String) ((JSONObject) gift).get("category");

                int quantity = -1;
                if (((JSONObject) gift).get("quantity") != null) {
                    quantity = (int) ((Long) ((JSONObject) gift).get("quantity")).intValue();
                }

                Gift newGift = new Gift(productName, price, category, quantity);
                santaGiftList.add(newGift);
            }
        }
        return santaGiftList;
    }

    /**
     * @return updates list from JSONArray
     */
    public static List<Update> returnUpdates(final JSONArray updates) {
        List<Update> childrenUpdates = new ArrayList<>();
        if (updates != null) {
            for (Object update : updates) {
                int id = -1;
                if (((JSONObject) update).get("id") != null) {
                    id = ((Long) ((JSONObject) update).get("id")).intValue();
                }
                double niceScore = -1;
                if (((JSONObject) update).get("niceScore") != null) {
                    niceScore = ((Long) ((JSONObject) update).get("niceScore")).doubleValue();
                }
                JSONArray giftCategories = (JSONArray) ((JSONObject) update)
                        .get("giftsPreferences");
                List<String> giftsPreferences = new ArrayList<>();
                for (Object giftCategory : giftCategories) {
                    String gift = (String) giftCategory;
                    giftsPreferences.add(gift);
                }
                String elf = ((String) ((JSONObject) update).get("elf"));
                Update newUpdate = new Update(id, niceScore, giftsPreferences, elf);
                childrenUpdates.add(newUpdate);
            }
        }
        return childrenUpdates;
    }
}
