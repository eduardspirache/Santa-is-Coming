package database;

import children.Child;
import northpole.Gift;

import java.util.List;

public class Database {
    private List<Child> childList;
    private List<Gift> giftList;

    public Database(List<Child> childList, List<Gift> giftList) {
        this.childList = childList;
        this.giftList = giftList;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }
}
