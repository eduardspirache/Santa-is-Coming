package children;

import java.util.List;

public final class Update {
    private final int id;
    private final double niceScore;
    private final List<String> giftsPreferences;
    private final String elf;

    public Update(final int id, final double niceScore, final List<String> giftsPreferences,
                  final String elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elf = elf;
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
