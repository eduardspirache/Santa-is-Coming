package northpole.elves;

import children.Child;
import northpole.Gift;

import java.util.List;

import static common.Constants.BLACK_ELF;
import static common.Constants.YELLOW_ELF;
import static common.Constants.PINK_ELF;

public final class ElfFactory {

    private ElfFactory() {
        // Constructor for checkstyle
    }

    /**
     * @return Elf type
     */
    public static Elf createElf(final Child child, final List<Gift> santaGiftList,
                                final double budget) {
        if (child.getElf().equals(YELLOW_ELF)) {
            return new YellowElf(child, santaGiftList);
        } else if (child.getElf().equals(BLACK_ELF)) {
            return new BlackElf(child, budget);
        } else if (child.getElf().equals(PINK_ELF)) {
            return new PinkElf(child, budget);
        } else {
            return new WhiteElf(child, budget);
        }
    }
}
