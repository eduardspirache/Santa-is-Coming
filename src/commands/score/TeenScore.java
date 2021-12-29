package commands.score;

import children.Child;
import commands.Command;

public final class TeenScore extends Score implements Command {
    public TeenScore(final Child child) {
        super(child);
    }

    @Override
    public void execute() {
        double numerator = 0;
        int denominator = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            numerator += scoreList.get(i) * (i + 1);
            denominator += i + 1;
        }
        super.score = numerator / denominator;
    }
}
