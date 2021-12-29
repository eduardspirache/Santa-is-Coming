package commands.score;

import children.Child;
import commands.Command;

import java.util.List;

public abstract class Score implements Command {
    protected final Child child;
    protected final List<Double> scoreList;
    protected double score;

    public Score(Child child) {
        this.child = child;
        this.scoreList = child.getScoreList();
        this.score = 0;
    }

    public double getAverageScore() {
        return score;
    }
}
