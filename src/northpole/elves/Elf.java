package northpole.elves;

import children.Child;
import commands.Command;
import northpole.Gift;

public abstract class Elf implements Command {
    protected final Child child;
    protected double budget;
    protected Gift gift;

    public Elf(final Child child, final double budget) {
        this.child = child;
        this.budget = budget;
        this.gift = null;
    }

    /**
     * Constructor for Yellow Elf since it only modifies the gift
     */
    public Elf(final Child child) {
        this.child = child;
        this.gift = null;
    }

    public double getBudget() {
        return budget;
    }

    public Gift getGift() {
        return gift;
    }
}
