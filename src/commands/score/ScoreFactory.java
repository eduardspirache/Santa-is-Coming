package commands.score;

import children.Child;

import static common.Constants.BABY_AGE;
import static common.Constants.KID_AGE;
import static common.Constants.TEEN_AGE;


public final class ScoreFactory {

    private ScoreFactory() {
        // Constructor for checkstyle
    }

    /**
     * @return type of child
     */
    public static Score createScore(final Child child) {
        if (child.getAge() < BABY_AGE) {
            return new BabyScore(child);
        } else if (child.getAge() < KID_AGE) {
            return new KidScore(child);
        } else if (child.getAge() <= TEEN_AGE) {
            return new TeenScore(child);
        } else {
            return new AdultScore(child);
        }
    }
}
