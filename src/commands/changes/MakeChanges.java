package commands.changes;

import children.Child;
import children.Update;
import commands.Command;
import input.Input;
import northpole.Change;

import java.util.List;

public class MakeChanges implements Command {
    private final Change change;
    private final Input input;
    private final List<Child> childList;

    public MakeChanges(Change change, Input input, List<Child> childList) {
        this.change = change;
        this.input = input;
        this.childList = childList;
    }

    @Override
    public void execute() {
        input.setSantaBudget(change.getNewSantaBudget());
        input.getGiftList().addAll(change.getNewGifts());
        input.getChildList().addAll(change.getNewChildren());

        for (Update update : change.getChildrenUpdates()) {
            for (Child child : childList) {
                if (child.getId() == update.getId()) {
                    if(update.getNiceScore() != -1) {
                        child.getScoreList().add(update.getNiceScore());
                    }
                    if(update.getGiftsPreferences() != null) {
                        child.getGiftsPreferences().removeIf(a -> update.getGiftsPreferences().contains(a));
                        child.getGiftsPreferences().addAll(0, update.getGiftsPreferences());
                    }
                    break;
                }
            }
        }
    }
}
