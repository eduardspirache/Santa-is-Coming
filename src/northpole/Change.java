package northpole;

import children.Child;
import children.Update;

import java.util.List;

public class Change {
    private final int newSantaBudget;
    private final List<Gift> newGifts;
    private final List<Child> newChildren;
    private final List<Update> childrenUpdates;

    public Change(int newSantaBudget, List<Gift> newGifts, List<Child> newChildren, List<Update> childrenUpdates) {
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
