package commands.budget;

import children.Child;
import commands.Command;
import commands.score.CalculateScore;

public final class CalculateChildBudget implements Command {
    private final Child child;
    private final double budgetUnit;
    private double budget;

    public CalculateChildBudget(final Child child, final double scoresSum,
                                final double santaBudget) {
        this.child = child;
        this.budgetUnit = santaBudget / scoresSum;
    }

    /**
     * Calculates the budget allocated for the child
     */
    @Override
    public void execute() {
        budget = child.getNiceScore() * budgetUnit;
    }

    public double getBudget() {
        return budget;
    }
}
