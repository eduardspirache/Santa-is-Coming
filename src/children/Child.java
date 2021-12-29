package children;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private final int id;
    private final String lastName;
    private final String firstName;
    private int age;
    private final String city;
    private final double niceScore;
    private List<Double> scoreList;
    private List<String> giftsPreferences;

    public Child(int id, String lastName, String firstName, int age, String city, double niceScore,
                 List<String> giftsPreferences) {
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

    public void setAge(int age) {
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

    public void setScoreList(List<Double> scoreList) {
        this.scoreList = scoreList;
    }

    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
