package commands.gifts;

import commands.Command;
import northpole.Gift;

import java.util.ArrayList;
import java.util.List;

public class SameCategoryGifts implements Command {
    private final List<Gift> santaGiftList;
    private final List<String> giftPreferences;
    private List<Gift> sameCategoryGifts;

    public SameCategoryGifts(List<Gift> santaGiftList, List<String> giftPreferences) {
        this.santaGiftList = santaGiftList;
        this.giftPreferences = giftPreferences;
        this.sameCategoryGifts = new ArrayList<>();
    }

    @Override
    public void execute() {

    }
}
