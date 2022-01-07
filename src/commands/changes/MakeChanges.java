package commands.changes;

import children.Child;
import children.Update;
import commands.Command;
import input.Input;
import northpole.Change;

import java.util.ArrayList;
import java.util.List;

public final class MakeChanges implements Command {
    private final Change change;
    private final Input input;
    private final List<Child> childList;

    public MakeChanges(final Change change, final Input input, final List<Child> childList) {
        this.change = change;
        this.input = input;
        this.childList = childList;
    }

    /**
     * Updates all the variables after changes
     * For the gift preferences, it firstly checks if the new gift preferences
     * have duplicates and removes them. Also, if the old preference list contains
     * categories from the new list, they will be removed.
     */
    @Override
    public void execute() {
        input.setSantaBudget(change.getNewSantaBudget());
        input.getGiftList().addAll(change.getNewGifts());
        input.getChildList().addAll(change.getNewChildren());
        input.setRoundStrategy(change.getStrategy());
        for (Update update : change.getChildrenUpdates()) {
            for (Child child : childList) {
                if (child.getId() == update.getId()) {
                    if (update.getNiceScore() != -1) {
                        child.getScoreList().add(update.getNiceScore());
                    }
                    if (update.getGiftsPreferences() != null) {
                        List<String> updatedGiftPreferences = new ArrayList<>();
                        for (String preference : update.getGiftsPreferences()) {
                            if (!updatedGiftPreferences.contains(preference)) {
                                updatedGiftPreferences.add(preference);
                            }
                        }
                        child.getGiftsPreferences().removeIf(updatedGiftPreferences::contains);
                        child.getGiftsPreferences().addAll(0, updatedGiftPreferences);
                    }
                    if(!update.getElf().equals(child.getElf())) {
                        child.setElf(update.getElf());
                    }
                    break;
                }
            }
        }

    }
}
