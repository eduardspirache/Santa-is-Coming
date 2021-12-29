package children;

import java.util.ArrayList;
import java.util.List;

public final class Child {
    private final int id;
    private final String lastName;
    private final String firstName;
    private int age;
    private final String city;
    private double niceScore;
    private final List<Double> scoreList;
    private List<String> giftsPreferences;

    public Child(final int id, final String lastName, final String firstName, final int age,
                 final String city, final double niceScore, final List<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        scoreList = new ArrayList<>();
        this.scoreList.add(niceScore);
        this.giftsPreferences = giftsPreferences;
    }

    public Child(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.age = child.getAge();
        this.city = child.getCity();
        this.niceScore = child.getNiceScore();
        scoreList = new ArrayList<>(child.getScoreList());
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public List<Double> getScoreList() {
        return scoreList;
    }

    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
