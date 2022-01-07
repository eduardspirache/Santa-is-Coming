package commands.gifts.strategy;

import children.Child;
import commands.gifts.SendChildListGifts;
import northpole.Santa;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class IdGiftStrategy implements GiftStrategy {
    private final List<Child> childList;
    private final Santa santa;

    public IdGiftStrategy(final List<Child> childList) {
        this.childList = new ArrayList<>(childList);
        this.santa = Santa.getInstance();
    }

    /**
     * Sorts the child list by ID (Already sorted from input)
     */
    @Override
    public JSONArray getGiftList() {
        SendChildListGifts sendChildListGifts = new SendChildListGifts(childList);
        sendChildListGifts.execute();
        return sendChildListGifts.getChildReceivedGiftsJSON();
    }
}
