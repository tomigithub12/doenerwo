package com.example.doenerwo.service;

public class DistanceCalculator {

    private double myLongitude;

    private double myLatitude;

    private double doenerLongitude;

    private double doenerLatitude;

    public static double calculateDistance(double myLongitude, double myLatitude, double doenerLongitude, double doenerLatitude){

        double ac = Math.abs(myLatitude - doenerLatitude);
        double cb = Math.abs(myLongitude - doenerLongitude);

        double distance = Math.hypot(ac, cb);

        System.out.print(distance);

        return Math.hypot(ac, cb);
    }
}