package input;

import children.Child;
import northpole.Gift;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class InputHelper {
    public static List<Child> returnChildren(JSONArray children) {
        List<Child> childList = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            int id = (int) children.get(0);
            String lastName = (String) children.get(1);
            String firstName = (String) children.get(2);
            int age = (int) children.get(3);
            String city = (String) children.get(4);
            double niceScore = (double) children.get(5);

            List<String> giftsPreferences = new ArrayList<>();
            JSONArray giftsFromJSON = (JSONArray) children.get(6);
            for (int j = 0; j < giftsFromJSON.size(); j ++) {
                String newCategory = (String) giftsFromJSON.get(j);
                giftsPreferences.add(newCategory);
            }
            Child newChild = new Child(id, lastName, firstName, age,
                    city, niceScore, giftsPreferences);
            childList.add(newChild);
        }
        return childList;
    }

    public static List<Gift> returnGifts(JSONArray gifts) {
        List<Gift> santaGiftList = new ArrayList<>();
        for (int i = 0; i < gifts.size(); i++) {
            String productName = (String) gifts.get(0);
            int price = (int) gifts.get(1);
            String category = (String) gifts.get(2);
            Gift newGift = new Gift(productName, price, category);
            santaGiftList.add(newGift);
        }
        return santaGiftList;
    }
}
