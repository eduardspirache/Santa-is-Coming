package commands.score;

import children.Child;
import commands.Command;

import static common.Constants.*;

public class CalculateScore implements Command {
    private Score score;
    private Child child;

    public CalculateScore(Child child) {
        this.child = child;
        this.score = ScoreFactory.createScore(child);
    }

    @Override
    public void execute() {
        score.execute();
    }

    public double getAverageScore() {
        return score.getAverageScore();
    }
}
