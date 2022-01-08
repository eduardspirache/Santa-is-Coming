package commands.gifts.strategy;

import children.Child;

import java.util.List;

import static common.Constants.ID;
import static common.Constants.NICE_SCORE;

public final class GiftStrategyFactory {

    private GiftStrategyFactory() {
        // Constructor for checkstyle
    }

    /**
     * Receives the type and returns the strategy
     */
    public static GiftStrategy createStrategy(final List<Child> childList, final String type) {
        if (type.equals(ID)) {
            return new IdGiftStrategy(childList);
        } else if (type.equals(NICE_SCORE)) {
            return new NiceScoreGiftStrategy(childList);
        } else {
            return new NiceCityGiftStrategy(childList);
        }
    }
}
