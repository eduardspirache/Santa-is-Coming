package commands.score;

import children.Child;
import commands.Command;

import static common.Constants.BABY_SCORE;

public final class BabyScore extends Score implements Command {
    public BabyScore(final Child child) {
        super(child);
    }

    @Override
    public void execute() {
        super.score = BABY_SCORE;
    }
}
