package com.lexaloris.backbase.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Coordination {
    @SerializedName("lon")
    private final double lon;
    @SerializedName("lat")
    private final double lat;

    public Coordination(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordination that = (Coordination) o;
        return Double.compare(that.lon, lon) == 0 &&
                Double.compare(that.lat, lat) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lon, lat);
    }

    @NonNull
    @Override
    public String toString() {
        return "Coordination{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
