package children;

import enums.Category;

import java.util.List;

public class Update {
    private final int id;
    private final double niceScore;
    private final List<String> giftsPreferences;

    public Update(int id, double niceScore, List<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public int getId() {
        return id;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }
}
