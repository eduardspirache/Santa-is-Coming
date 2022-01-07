package commands.gifts.strategy;

import northpole.Gift;
import org.json.simple.JSONArray;

import java.util.List;

public interface GiftStrategy {
    /**
     * @return list of gifts a child received
     */
    JSONArray getGiftList();
}
