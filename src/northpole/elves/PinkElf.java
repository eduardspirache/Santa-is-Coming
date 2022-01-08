package northpole.elves;

import children.Child;
import commands.Command;

import static common.Constants.HUNDRED;
import static common.Constants.THIRTY;

public final class PinkElf extends Elf implements Command {
    public PinkElf(final Child child, final double budget) {
        super(child, budget);
    }

    @Override
    public void execute() {
        super.budget = budget + budget * THIRTY / HUNDRED;
    }
}
