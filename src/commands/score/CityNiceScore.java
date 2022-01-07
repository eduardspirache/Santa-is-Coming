package commands.score;

import children.Child;
import commands.Command;
import enums.Cities;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class CityNiceScore implements Command {
    private HashMap<String, Double> cityScores;
    private final List<Child> childList;
    private final Cities allCities[];

    public CityNiceScore(final List<Child> childList) {
        this.cityScores = new HashMap<>();
        this.childList = childList;
        this.allCities = Cities.values();
    }

    public HashMap<String, Double> getCityScores() {
        return cityScores;
    }

    public double getOneCityScore(String city) {
        return cityScores.get(city);
    }

    @Override
    public void execute() {
        for(Cities city : allCities) {
            double sum = 0;
            int count = 0;
            for(Child child : childList) {
                if(child.getCity().equals(city.getValue())) {
                    sum += child.getNiceScore();
                    count ++;
                }
            }
            if(count > 0) {
                cityScores.put(city.getValue(), sum / count);
            }

        }
        cityScores = cityScores
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }
}
