package com.codeup.safewalk.models;

public class TwilioData {
    private String latitude;
    private String longitude;
    private String buttonType;

    public TwilioData(String latitude, String longitude, String buttonType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.buttonType = buttonType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
}

