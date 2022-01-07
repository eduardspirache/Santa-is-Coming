package commands.score;

import children.Child;
import commands.Command;

public final class CalculateScore implements Command {
    private Score score;
    private final Child child;

    public CalculateScore(final Child child) {
        this.child = child;
        this.score = ScoreFactory.createScore(child);
    }

    /**
     * Calculates each child's score, including the bonus
     */
    @Override
    public void execute() {
        score.execute();
        child.setNiceScore(score.getAverageScore() + child.getNiceScoreBonus());
    }

    public double getAverageScore() {
        return score.getAverageScore();
    }
}
