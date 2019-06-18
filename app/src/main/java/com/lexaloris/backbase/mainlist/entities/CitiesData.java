package com.lexaloris.backbase.mainlist.entities;

import java.util.ArrayList;

public class CitiesData {
    private final Cities cities;
    private final ArrayList<String> citiesNames;

    public CitiesData(Cities cities) {
        this.cities = cities;
        this.citiesNames = mapCitiesNames(cities);
    }

    private ArrayList<String> mapCitiesNames(Cities allCities) {
        ArrayList<String> citiesNames = new ArrayList<>();
        for (int i = 0; i < allCities.size(); i++) {
            citiesNames.add(allCities.get(i).getName());
        }
        return citiesNames;
    }

    public Cities getCities() {
        return cities;
    }

    public ArrayList<String> getCitiesNames() {
        return citiesNames;
    }
}
