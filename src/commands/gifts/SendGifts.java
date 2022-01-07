package commands.gifts;

import children.Child;
import commands.Command;
import northpole.Gift;

import java.util.ArrayList;
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
     * Function iterates through both the list of child's gift categories and
     * the list of gifts Santa can provide, searching for those that can be found
     * in both lists.
     * It tries to give the cheapest gift from each category if the quantity is positive.
     * If not, it tries to give the next cheapest if quantity > 0 etc.
     * It stores all the gifts the child receives in a list.
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
                for(int i = 0; i < sameCategoryGiftsList.size(); i ++) {
                    gift = sameCategoryGiftsList.get(i);
                    if(gift.getQuantity() > 0 && budget >= gift.getPrice()) {
                        receivedGifts.add(gift);
                        budget -= gift.getPrice();
                        gift.setQuantity(gift.getQuantity() - 1);
                        break;
                    }
                }
            }
        }
    }
}



