package commands.gifts;

import commands.Command;
import northpole.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class SameCategoryGifts implements Command {
    private final List<Gift> santaGiftList;
    private final String giftCategory;
    private List<Gift> sameCategoryGifts;

    public SameCategoryGifts(final List<Gift> santaGiftList, final String giftCategory) {
        this.santaGiftList = santaGiftList;
        this.giftCategory = giftCategory;
        this.sameCategoryGifts = new ArrayList<>();
    }

    public List<Gift> getSameCategoryGifts() {
        return sameCategoryGifts;
    }

    /**
     * It adds the gifts that fall in the same category in a list, sorts the list
     * ascending by price value and saves the gift with the lowest price.
     */
    @Override
    public void execute() {
        for (Gift santaGift : santaGiftList) {
            if (santaGift.getCategory().equals(giftCategory)) {
                sameCategoryGifts.add(santaGift);
            }
        }
        sameCategoryGifts.sort(Comparator.comparing(Gift::getPrice));
    }
}
