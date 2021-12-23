package input;

import children.Child;
import children.Update;
import northpole.Change;
import northpole.Gift;

import java.util.ArrayList;
import java.util.List;

public class Input {
    private int numberOfYears;
    private double santaBudget;
    private List<Child> childList;
    private List<Gift> giftList;
    private List<Change> annualChanges;

    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0.0;
        this.childList = new ArrayList<>();
        this.giftList = new ArrayList<>();
        this.annualChanges = new ArrayList<>();
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(double santaBudget) {
        this.santaBudget = santaBudget;
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

    public List<Change> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(List<Change> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
