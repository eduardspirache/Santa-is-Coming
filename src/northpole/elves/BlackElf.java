package northpole.elves;

import children.Child;
import commands.Command;

public class BlackElf extends Elf implements Command {

    public BlackElf(final Child child, final double budget) {
        super(child, budget);
    }

    @Override
    public void execute() {
        super.budget = budget - budget * 30 / 100;
    }
}
