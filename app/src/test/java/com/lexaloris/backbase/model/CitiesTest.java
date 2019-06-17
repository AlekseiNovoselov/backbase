package com.lexaloris.backbase.model;

import com.google.gson.Gson;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CitiesTest {

    private Gson gson = new Gson();

    @Test
    public void parseCitiesJson() {
        String json = createCitiesJson();
        Cities expectedResult = createCitiesModel();

        Cities actualResult = gson.fromJson(json, Cities.class);

        assertEquals(expectedResult, actualResult);
    }

    private String createCitiesJson() {
        return "[\n" +
                "{\n" +
                "    \"country\":\"UA\",\n" +
                "    \"name\":\"Hurzuf\",\n" +
                "    \"_id\":707860,\n" +
                "    \"coord\":{\n" +
                "            \"lon\":34.283333,\n" +
                "        \"lat\":44.549999\n" +
                "    }\n" +
                "}\n" +
                "]";
    }

    private Cities createCitiesModel() {
        Coordination coordination = new Coordination(34.283333, 44.549999);
        City city = new City("Hurzuf", "UA", 707860, coordination);
        Cities expectedResult = new Cities();
        expectedResult.add(city);
        return expectedResult;
    }
}
