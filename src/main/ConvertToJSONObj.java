package main;

import children.Child;
import commands.Command;
import northpole.Gift;
import northpole.Santa;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class ConvertToJSONObj implements Command {
    private final Child child;
    private final Santa santa;
    private JSONObject jsonChild;

    public ConvertToJSONObj(final Child child) {
        this.child = child;
        this.santa = Santa.getInstance();
        this.jsonChild = new JSONObject();
    }

    public JSONObject getJsonChild() {
        return jsonChild;
    }

    @Override
    public void execute() {
        Child copyChild = new Child(child);
        jsonChild.put("id", copyChild.getId());
        jsonChild.put("lastName", copyChild.getLastName());
        jsonChild.put("firstName", copyChild.getFirstName());
        jsonChild.put("city", copyChild.getCity());
        jsonChild.put("age", copyChild.getAge());
        jsonChild.put("giftsPreferences", copyChild.getGiftsPreferences());
        jsonChild.put("averageScore", child.getNiceScore());
        jsonChild.put("niceScoreHistory", copyChild.getScoreList());
        jsonChild.put("assignedBudget", santa.getBudgetList().get(child.getId()));
        JSONArray receivedJSONGifts = new JSONArray();
        for (Gift gift : child.getReceivedRoundGifts()) {
            if (gift != null) {
                JSONObject jsonGift = new JSONObject();
                jsonGift.put("productName", gift.getProductName());
                jsonGift.put("price", gift.getPrice());
                jsonGift.put("category", gift.getCategory());
                receivedJSONGifts.add(jsonGift);
            }
        }
        jsonChild.put("receivedGifts", receivedJSONGifts);
    }
}
