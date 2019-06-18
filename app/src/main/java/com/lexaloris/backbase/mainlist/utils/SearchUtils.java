package com.lexaloris.backbase.mainlist.utils;

import com.lexaloris.backbase.mainlist.entities.Cities;
import com.lexaloris.backbase.mainlist.entities.CitiesData;

import java.util.ArrayList;

public class SearchUtils {
    private final BinarySearchLeftMore binarySearchLeftMore = new BinarySearchLeftMore();
    private final BinarySearchRightMore binarySearchRightMore = new BinarySearchRightMore();

    public Cities startWithPrefix(CitiesData citiesData, String prefixName) {
        Cities cities = citiesData.getCities();
        String lowerCasePrefixName =  prefixName.toLowerCase();
        ArrayList<String> citiesNames = citiesData.getCitiesNames();
        int size = cities.size();
        int left = binarySearchLeftMore.findIndex(citiesNames, 0, size - 1, lowerCasePrefixName);
        int right = binarySearchRightMore.findIndex(citiesNames, 0, size - 1, lowerCasePrefixName, size);
        if (left == -1 && right == -1) {
            return new Cities();
        }
        Cities filteredCities = new Cities();
        filteredCities.addAll(cities.subList(left, right + 1));
        return filteredCities;
    }
}
