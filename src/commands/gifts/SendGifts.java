package commands.gifts;

import children.Child;
import commands.Command;
import northpole.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class SendGifts implements Command {
    private double budget;
    private final List<Gift> santaGiftList;
    private final List<Gift> receivedGifts;
    private final List<String> giftPreferences;

    public SendGifts(final Child child, final double budget, final List<Gift> santaGiftList) {
        this.budget = budget;
        this.santaGiftList = santaGiftList;
        this.receivedGifts = new ArrayList<>();
        this.giftPreferences = child.getGiftsPreferences();
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * Function iterates through both the list of children gift categories and
     * the list of gifts Santa can provide, searching for those that can be found
     * in both lists.
     * It stores all the gifts children receive in a list.
     */
    @Override
    public void execute() {
        for (String giftCategory : giftPreferences) {
            SameCategoryGifts sameCategoryGifts = new SameCategoryGifts(santaGiftList,
                    giftCategory);
            sameCategoryGifts.execute();
            List<Gift> sameCategoryGiftsList = sameCategoryGifts.getSameCategoryGifts();

            Gift gift;
            if (sameCategoryGiftsList.size() > 0) {
                gift = sameCategoryGiftsList.get(0);
                if (budget >= gift.getPrice()) {
                    receivedGifts.add(gift);
                    budget -= gift.getPrice();
                }
            }
        }
    }
}



