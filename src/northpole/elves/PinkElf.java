package northpole.elves;

import children.Child;
import commands.Command;

public class PinkElf extends Elf implements Command {
    public PinkElf(final Child child, final double budget) {
        super(child, budget);
    }

    @Override
    public void execute() {
        super.budget = budget + budget * 30 / 100;
    }
}
