package northpole;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Santa {
    private static Santa instance = null;
    private double santaBudget;
    private double averageScoresSum;
    private List<Gift> giftList;
    private Map<Integer, Double> budgetList;

    private Santa() {
        this.santaBudget = 0;
        this.averageScoresSum = 0;
        this.budgetList = new LinkedHashMap<>();
        this.giftList = null;
    }

    /**
     * Lazy Singleton
     */
    public static Santa getInstance() {
        if(instance == null) {
            instance = new Santa();
        }
        return instance;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public double getAverageScoresSum() {
        return averageScoresSum;
    }

    public void setAverageScoresSum(double averageScoresSum) {
        this.averageScoresSum = averageScoresSum;
    }

    public Map<Integer, Double> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(Map<Integer, Double> budgetList) {
        this.budgetList = budgetList;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }
}
