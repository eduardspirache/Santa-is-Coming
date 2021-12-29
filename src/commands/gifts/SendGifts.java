package commands.gifts;

import children.Child;
import commands.Command;
import northpole.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SendGifts implements Command {
    private double budget;
    private final List<Gift> santaGiftList;
    private final List<Gift> receivedGifts;
    private final List<String> giftPreferences;

    public SendGifts(Child child, double budget, List<Gift> santaGiftList) {
        this.budget = budget;
        this.santaGiftList = santaGiftList;
        this.receivedGifts = new ArrayList<>();
        this.giftPreferences = child.getGiftsPreferences();
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     *  Function iterates through both the list of children gift categories and
     *  the list of gifts Santa can provide, searching for those that can be found
     *  in both lists.
     *  It adds the gifts that fall in the same category in a list, sorts the list
     *  ascending by price value and saves the gift with the lowest price.
     *  It stores all the gifts children receive in a list.
     */
    @Override
    public void execute() {
        for (String giftCategory : giftPreferences) {
            Gift gift;
            List<Gift> sameCategoryGifts = new ArrayList<>();
            for (Gift santaGift : santaGiftList) {
                if (santaGift.getCategory().equals(giftCategory)) {
                    sameCategoryGifts.add(santaGift);
                }
            }
            sameCategoryGifts.sort(Comparator.comparing(Gift::getPrice));
            if(sameCategoryGifts.size() > 0) {
                gift = sameCategoryGifts.get(0);
                if(budget >= gift.getPrice()) {
                    receivedGifts.add(gift);
                    budget -= gift.getPrice();
                }
            }
        }
    }
}



