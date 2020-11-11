package com.example.test;

public class WeatherInfo {
    private String temperature;
    private String CloudCoverage;
    private String ValidTime;

    public WeatherInfo(String temperature, String ValidTime) {
        this.temperature = temperature;
        this.CloudCoverage = CloudCoverage;
        this.ValidTime = ValidTime;
    }
    public WeatherInfo(){}

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCloudCoverage() {
        return CloudCoverage;
    }

    public void setCloudCoverage(String cloudCoverage) {
        CloudCoverage = cloudCoverage;
    }

    public String getValidTime() {
        return ValidTime;
    }

    public void setValidTime(String validTime) {
        ValidTime = validTime;
    }
}
