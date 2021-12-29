package commands.score;

import children.Child;
import commands.Command;

public final class KidScore extends Score implements Command {
    public KidScore(final Child child) {
        super(child);
    }

    @Override
    public void execute() {
        int count = 0;
        double sum = 0;
        for (double score : super.scoreList) {
            count++;
            sum += score;
        }
        super.score = sum / count;
    }
}
