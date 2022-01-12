package children;

public final class City {
    private final String name;
    private final double score;

    public City(final String name, final double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
