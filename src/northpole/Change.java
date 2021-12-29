package northpole;

import children.Child;
import children.Update;

import java.util.List;

public final class Change {
    private final int newSantaBudget;
    private final List<Gift> newGifts;
    private final List<Child> newChildren;
    private final List<Update> childrenUpdates;

    public Change(final int newSantaBudget, final List<Gift> newGifts,
                  final List<Child> newChildren, final List<Update> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public int getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public List<Update> getChildrenUpdates() {
        return childrenUpdates;
    }
}
