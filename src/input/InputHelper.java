package input;

import children.Child;
import northpole.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InputHelper {
    public static List<Child> returnChildren(JSONArray children) {
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
                    niceScore = ((Long)((JSONObject) child).get("niceScore")).doubleValue();

                }
                List<String> giftsPreferences = new ArrayList<>();
                JSONArray giftsFromJSON = (JSONArray) ((JSONObject) child).get("giftsPreferences");
                if (giftsFromJSON != null) {
                    for (Object o : giftsFromJSON) {
                        String newCategory = (String) o;
                        giftsPreferences.add(newCategory);
                    }
                }
                Child newChild = new Child(id, lastName, firstName, age,
                        city, niceScore, giftsPreferences);
                childList.add(newChild);
            }
        }
        return childList;
    }

    public static List<Gift> returnGifts(JSONArray gifts) {
        List<Gift> santaGiftList = new ArrayList<>();
        if (gifts != null) {
            for (Object gift : gifts) {
                String productName = (String) ((JSONObject) gift).get("productName");

                int price = -1;
                if (((JSONObject) gift).get("price") != null) {
                    price = (int) ((Long)((JSONObject) gift).get("price")).intValue();
                }

                String category = (String) ((JSONObject) gift).get("category");

                Gift newGift = new Gift(productName, price, category);
                santaGiftList.add(newGift);
            }
        }
        return santaGiftList;
    }
}
