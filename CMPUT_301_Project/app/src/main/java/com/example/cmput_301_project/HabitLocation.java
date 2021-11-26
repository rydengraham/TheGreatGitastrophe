package com.example.cmput_301_project;

public class HabitLocation {
    private String locationName;
    private String address;
    private String longitude;
    private String latitude;


    public HabitLocation(String address, String longitude, String latitude){
        locationName = "Home";
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public HabitLocation(String locationName,String address, String longitude, String latitude){
        this.locationName = locationName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;

    }


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
