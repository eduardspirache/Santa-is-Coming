package commands.budget;

import children.Child;
import commands.Command;
import commands.score.CalculateScore;

public class CalculateChildBudget implements Command {
    private final Child child;
    private final double budgetUnit;
    private double budget;

    public CalculateChildBudget(Child child, double scoresSum, double santaBudget) {
        this.child = child;
        this.budgetUnit = santaBudget / scoresSum;
    }

    @Override
    public void execute() {
        CalculateScore calculateScore = new CalculateScore(child);
        calculateScore.execute();
        budget = calculateScore.getAverageScore() * budgetUnit;
    }

    public double getBudget() {
        return budget;
    }
}
