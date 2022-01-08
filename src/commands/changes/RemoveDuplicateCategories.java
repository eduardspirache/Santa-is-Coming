package commands.changes;

import children.Child;
import commands.Command;

import java.util.ArrayList;
import java.util.List;

public final class RemoveDuplicateCategories implements Command {
    private final List<Child> childList;

    public RemoveDuplicateCategories(final List<Child> childList) {
        this.childList = childList;
    }

    @Override
    public void execute() {
        for (Child child : childList) {
            List<String> giftCategories = new ArrayList<>();
            for (String category : child.getGiftsPreferences()) {
                if (!giftCategories.contains(category)) {
                    giftCategories.add(category);
                }
            }
            child.setGiftsPreferences(giftCategories);
        }
    }
}
