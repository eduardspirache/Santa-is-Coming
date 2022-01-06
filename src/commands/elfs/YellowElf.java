package commands.elfs;

import children.Child;
import commands.Command;
import northpole.Gift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class YellowElf extends Elf implements Command {
    private final List<Gift> santaGiftList;
    public YellowElf(final Child child, final List<Gift> santaGiftList) {
        super(child);
        this.santaGiftList = santaGiftList;
    }

    @Override
    public void execute() {

    }
}
