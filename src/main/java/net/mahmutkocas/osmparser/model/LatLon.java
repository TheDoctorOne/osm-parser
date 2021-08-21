package net.mahmutkocas.osmparser.model;


/**
 * Use fromRadians or fromDegrees to create instance.
 * */
public class LatLon {
    public static double DEG2RAD = Math.PI / 180;
    public static double RAD2DEG = 180 / Math.PI;

    private double lat = 0.0d;
    private double lon = 0.0d;

    private LatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public static LatLon fromRadians(double lat, double lon) {
        return new LatLon(lat * RAD2DEG, lon * RAD2DEG);
    }

    public static LatLon fromDegrees(double lat, double lon) {
        return new LatLon(lat, lon);
    }

    /**
     * As degrees.
     * */
    public double getLat() {
        return lat;
    }

    /**
     * As degrees.
     * */
    public double getLon() {
        return lon;
    }

    public double getLatAsRad() {
        return lat * DEG2RAD;
    }

    public double getLonAsRad() {
        return lon * DEG2RAD;
    }


}
