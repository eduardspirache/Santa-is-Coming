package commands.score;

import children.Child;
import commands.Command;

public class AdultScore extends Score implements Command {
    public AdultScore(Child child) {
        super(child);
    }

    @Override
    public void execute() {
        super.score = -1;
    }
}
