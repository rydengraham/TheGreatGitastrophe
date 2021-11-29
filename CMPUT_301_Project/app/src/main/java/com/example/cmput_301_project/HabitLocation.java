package com.example.cmput_301_project;

/**
 *  HabitLocation object to handle locations specified by user
 */
public class HabitLocation {
    private String locationName;
    private String address;
    private String longitude;
    private String latitude;

    public HabitLocation(String address, String longitude, String latitude){
        this.locationName = locationName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public HabitLocation(String locationName,String address, String latitude,String longitude){
        this.locationName = locationName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public HabitLocation(HabitLocation otherLocation){
        this.locationName = otherLocation.getLocationName();
        this.address = otherLocation.getAddress();
        this.longitude = otherLocation.getLongitude();
        this.latitude = otherLocation.getLatitude();
    }

    public HabitLocation() { /* Required empty public constructor */ }

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
