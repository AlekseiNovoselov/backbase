package com.lexaloris.backbase.mainlist.utils;

import com.lexaloris.backbase.mainlist.entities.Cities;
import com.lexaloris.backbase.mainlist.entities.CitiesData;
import com.lexaloris.backbase.mainlist.entities.City;
import com.lexaloris.backbase.mainlist.entities.Coordination;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SearchUtilsTest {
    private final SearchUtils searchUtils = new SearchUtils();

    @Test
    public void returnNothingInEmptyList() {
        Cities cities = new Cities();
        CitiesData citiesData = new CitiesData(cities);
        Cities actualResult = searchUtils.startWithPrefix(citiesData, "");
        Cities expectedResult = new Cities();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void returnAllCitiesInEmptySearch() {
        Cities actualResult = searchUtils.startWithPrefix(fullCitiesData(), "");
        Cities expectedResult = fullCitiesList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void returnAllCitiesStartedWithA() {
        Cities expectedResult = new Cities();
        expectedResult.addAll(Arrays.asList(alabama(), albuquerque(), anaheim(), arizona()));
        Cities actualResult = searchUtils.startWithPrefix(fullCitiesData(), "a");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void returnAllCitiesStartedWithAl() {
        Cities expectedResult = new Cities();
        expectedResult.addAll(Arrays.asList(alabama(), albuquerque()));
        Cities actualResult = searchUtils.startWithPrefix(fullCitiesData(), "al");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void returnAllCitiesStartedWithAlb() {
        Cities expectedResult = new Cities();
        expectedResult.addAll(Collections.singletonList(albuquerque()));
        Cities actualResult = searchUtils.startWithPrefix(fullCitiesData(), "alb");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void returnAllCitiesStartedWithSyd() {
        Cities expectedResult = new Cities();
        expectedResult.addAll(Collections.singletonList(sydney()));
        Cities actualResult = searchUtils.startWithPrefix(fullCitiesData(), "Syd");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void returnAllCitiesStartedWithSydneyka() {
        Cities expectedResult = new Cities();
        Cities actualResult = searchUtils.startWithPrefix(fullCitiesData(), "Sydneyka");
        assertEquals(expectedResult, actualResult);
    }

    private CitiesData fullCitiesData() {
        return new CitiesData(fullCitiesList());
    }

    private Cities fullCitiesList() {
        Cities cities = new Cities();
        cities.addAll(Arrays.asList(alabama(), albuquerque(), anaheim(), arizona(), sydney()));
        return cities;
    }

    private City alabama() {
        return new City("Alabama", "US", 0, mockCoordination());
    }

    private City albuquerque() {
        return new City("Albuquerque", "US", 0, mockCoordination());
    }

    private City anaheim() {
        return new City("Anaheim", "US", 0, mockCoordination());
    }

    private City arizona() {
        return new City("Arizona", "US", 0, mockCoordination());
    }

    private City sydney() {
        return new City("Sydney", "AU", 0, mockCoordination());
    }

    private Coordination mockCoordination() {
        return new Coordination(0, 0);
    }
}
