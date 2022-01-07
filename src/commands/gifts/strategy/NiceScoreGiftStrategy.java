package commands.gifts.strategy;

import children.Child;
import commands.gifts.SendChildListGifts;
import commands.score.CityNiceScore;
import northpole.Santa;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class NiceScoreGiftStrategy implements GiftStrategy {
    private final List<Child> childList;
    private final Santa santa;

    public NiceScoreGiftStrategy(final List<Child> childList) {
        this.childList = new ArrayList<>(childList);
        this.santa = Santa.getInstance();
    }

    @Override
    public JSONArray getGiftList() {
        childList.sort((a, b) -> {
            if (a.getNiceScore() == b.getNiceScore() && a.getCity().equals(b.getCity())) {
                return Double.compare(a.getId(), b.getId());
            } else if (a.getNiceScore() != b.getNiceScore()) {
                return Double.compare(b.getNiceScore(), a.getNiceScore());
            }
            CityNiceScore cities = new CityNiceScore(childList);
            cities.execute();
            return Double.compare(cities.getOneCityScore(b.getCity()),
                    cities.getOneCityScore(a.getCity()));
        });

        SendChildListGifts sendChildListGifts = new SendChildListGifts(childList);
        sendChildListGifts.execute();
        return sendChildListGifts.getChildReceivedGiftsJSON();
    }
}
