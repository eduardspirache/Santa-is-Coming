package commands.elfs;

import children.Child;
import commands.Command;

public class WhiteElf extends Elf implements Command {
    public WhiteElf(final Child child, final double budget) {
        super(child, budget);
    }

    @Override
    public void execute() {}
}
