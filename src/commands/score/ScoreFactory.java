package commands.score;

import children.Child;

import static common.Constants.*;

public class ScoreFactory {
    public static Score createScore(Child child) {
        if(child.getAge() < BABY_AGE) {
            return new BabyScore(child);
        } else if (child.getAge() <= KID_AGE) {
            return new KidScore(child);
        } else if (child.getAge() <= TEEN_AGE) {
            return new TeenScore(child);
        } else {
            return new AdultScore(child);
        }
    }
}
