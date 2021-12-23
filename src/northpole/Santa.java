package northpole;

import java.util.List;

public class Santa {
    private int santaBudget;
    private List<Gift> santaGiftList;

    public Santa(int santaBudget, List<Gift> santaGiftList) {
        this.santaBudget = santaBudget;
        this.santaGiftList = santaGiftList;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public List<Gift> getSantaGiftList() {
        return santaGiftList;
    }
}
