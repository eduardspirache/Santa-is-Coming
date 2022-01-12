package commands.gifts.strategy;

import children.Child;
import children.City;
import commands.gifts.SendChildListGifts;
import commands.score.CityNiceScore;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;


public final class NiceCityGiftStrategy implements GiftStrategy {
    private final List<Child> childList;

    public NiceCityGiftStrategy(final List<Child> childList) {
        this.childList = new ArrayList<>(childList);
    }

    @Override
    public JSONArray getGiftList() {
        CityNiceScore cities = new CityNiceScore(childList);
        cities.execute();

        // If there are two cities with the same score, we sort them lexicographically
        List<City> cityList = cities.getCityScores();

        // We sort the list by town and then by id
        List<Child> orderedList = new ArrayList<>();
        for (var entry : cityList) {
            for (Child child : childList) {
                if (child.getCity().equals(entry.getName())) {
                    orderedList.add(child);
                }
            }
        }

        SendChildListGifts sendChildListGifts = new SendChildListGifts(orderedList);
        sendChildListGifts.execute();
        return sendChildListGifts.getChildReceivedGiftsJSON();
    }
}
