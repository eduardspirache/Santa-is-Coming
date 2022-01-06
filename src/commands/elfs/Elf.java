package commands.elfs;

import children.Child;
import northpole.Gift;

public abstract class Elf {
    protected final Child child;
    protected double budget;
    protected Gift gift;

    public Elf(Child child) {
        this.child = child;
        this.budget = -1;
        this.gift = null;
    }
}
