package commands.gifts.strategy;

import org.json.simple.JSONArray;

public interface GiftStrategy {
    /**
     * @return list of gifts a child received
     */
    JSONArray getGiftList();
}
