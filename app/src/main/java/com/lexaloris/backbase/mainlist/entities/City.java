package com.lexaloris.backbase.mainlist.entities;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class City {
    @SerializedName("name")
    private String name;
    @SerializedName("country")
    private String countryName;
    @SerializedName("_id")
    private long id;
    @SerializedName("coord")
    private Coordination coordination;

    public City(String name, String countryName, long id, Coordination coordination) {
        this.name = name;
        this.countryName = countryName;
        this.id = id;
        this.coordination = coordination;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

    public long getId() {
        return id;
    }

    public Coordination getCoordination() {
        return coordination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name) &&
                Objects.equals(countryName, city.countryName) &&
                Objects.equals(coordination, city.coordination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryName, id, coordination);
    }

    @NonNull
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", countryName='" + countryName + '\'' +
                ", id=" + id +
                ", coordination=" + coordination +
                '}';
    }
}
