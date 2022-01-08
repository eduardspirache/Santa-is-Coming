package commands.score;

import children.Child;
import commands.Command;

import static common.Constants.HUNDRED;
import static common.Constants.TEN;

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
        if (score.getAverageScore()
                + score.getAverageScore() * child.getNiceScoreBonus() / HUNDRED >= TEN) {
            child.setNiceScore(TEN);
        } else {
            child.setNiceScore(score.getAverageScore()
                    + score.getAverageScore() * child.getNiceScoreBonus() / HUNDRED);
        }
    }

    public double getAverageScore() {
        return score.getAverageScore();
    }
}
