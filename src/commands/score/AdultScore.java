package commands.score;

import children.Child;
import commands.Command;

public final class AdultScore extends Score implements Command {
    public AdultScore(final Child child) {
        super(child);
    }

    @Override
    public void execute() {
        super.score = -1;
    }
}
