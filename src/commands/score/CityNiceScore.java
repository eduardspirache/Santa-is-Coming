package commands.score;

import children.Child;
import children.City;
import commands.Command;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;


public final class CityNiceScore implements Command {
    private List<City> cityScores;
    private final List<Child> childList;
    private final Cities allCities[];

    public CityNiceScore(final List<Child> childList) {
        this.cityScores = new ArrayList<>();
        this.childList = childList;
        this.allCities = Cities.values();
    }

    public List<City> getCityScores() {
        return cityScores;
    }

    @Override
    public void execute() {
        for (Cities city : allCities) {
            double sum = 0;
            int count = 0;
            for (Child child : childList) {
                if (child.getCity().equals(city.getValue())) {
                    sum += child.getNiceScore();
                    count++;
                }
            }
            if (count > 0) {
                cityScores.add(new City(city.getValue(), sum / count));
            }

        }
        cityScores.sort((a, b) -> {
            if (a.getScore() == b.getScore()) {
                return a.getName().compareTo(b.getName());
            }
            return Double.compare(b.getScore(), a.getScore());
        });
    }
}
