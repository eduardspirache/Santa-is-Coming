package commands.budget;

import children.Child;
import commands.Command;
import commands.score.CalculateScore;

import java.util.List;

public class CalculateScoresSum implements Command {
    private double sum;
    private final List<Child> childList;

    public CalculateScoresSum(List<Child> scoreList) {
        this.sum = 0;
        this.childList = scoreList;
    }

    @Override
    public void execute() {
        for(Child child : childList) {
            CalculateScore calculateScore = new CalculateScore(child);
            calculateScore.execute();
            this.sum += calculateScore.getAverageScore();
        }
    }

    public double getSum() {
        return sum;
    }
}
