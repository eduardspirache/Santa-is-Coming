package commands.gifts.strategy;

import children.Child;
import commands.gifts.SendChildListGifts;
import commands.score.CityNiceScore;
import northpole.Santa;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class NiceCityGiftStrategy implements GiftStrategy {
    private final List<Child> childList;
    private final Santa santa;

    public NiceCityGiftStrategy(final List<Child> childList) {
        this.childList = new ArrayList<>(childList);
        this.santa = Santa.getInstance();
    }

    @Override
    public JSONArray getGiftList() {
        CityNiceScore cities = new CityNiceScore(childList);
        cities.execute();

        // If there are two cities with the same score, we sort them lexicographically
        HashMap<String, Double> map = cities.getCityScores();
        Map.Entry<String, Double> prev = null;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (prev != null) {
                if (prev.getValue() == entry.getValue()) {
                    if (prev.getKey().compareTo(entry.getKey()) > 0) {
                        prev.setValue(entry.setValue(prev.getValue()));
                    }
                }
            }
            prev = entry;
        }

        // We sort the list by town and then by id
        List<Child> orderedList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            for (Child child : childList) {
                if (child.getCity().equals(entry.getKey())) {
                    orderedList.add(child);
                }
            }
        }

        SendChildListGifts sendChildListGifts = new SendChildListGifts(orderedList);
        sendChildListGifts.execute();
        return sendChildListGifts.getChildReceivedGiftsJSON();
    }
}
