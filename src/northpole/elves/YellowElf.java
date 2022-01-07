package northpole.elves;

import children.Child;
import commands.Command;
import commands.gifts.SameCategoryGifts;
import northpole.Gift;

import java.util.List;

public class YellowElf extends Elf implements Command {
    private final List<Gift> santaGiftList;

    public YellowElf(final Child child, final List<Gift> santaGiftList) {
        super(child);
        this.santaGiftList = santaGiftList;
    }

    /**
     * It iterates through the child's gift preferences in the order they were read at input,
     * checks if santaGiftList contains gifts in those categories and if it contains, checks if
     * the quantity is positive, assigns gift and reduces quantity by 1.
     * If it isn't, it goes to the next category.
     */
    @Override
    public void execute() {
        for(String giftPreference : child.getGiftsPreferences()) {
            SameCategoryGifts sameCategoryGifts = new SameCategoryGifts(santaGiftList, giftPreference);
            sameCategoryGifts.execute();

            Gift thisGift;
            if(sameCategoryGifts.getSameCategoryGifts().size() > 0) {
                thisGift = sameCategoryGifts.getSameCategoryGifts().get(0);
                if(thisGift.getQuantity() > 0) {
                    super.gift = thisGift;
                    thisGift.setQuantity(thisGift.getQuantity() - 1);
                    break;
                }
            }
        }
    }
}
