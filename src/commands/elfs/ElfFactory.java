package commands.elfs;

import children.Child;
import northpole.Gift;

import java.util.List;

public class ElfFactory {

    private ElfFactory() {
        // Constructor for checkstyle
    }


    public static Elf createElf(final Child child, final List<Gift> santaGiftList,
                                final double budget) {
        if (child.getElf().equals("yellow")) {
            return new YellowElf(child, santaGiftList);
        } else if (child.getElf().equals("black")) {
            return new BlackElf(child, budget);
        } else if (child.getElf().equals("pink")) {
            return new PinkElf(child, budget);
        } else {
            return new WhiteElf(child, budget);
        }
    }
}
