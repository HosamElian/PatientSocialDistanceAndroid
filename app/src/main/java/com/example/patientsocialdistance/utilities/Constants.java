package com.example.patientsocialdistance.utilities;

public class Constants {

    public static String getUrl(String controllerName) {
        return String.format("https://192.168.1.2:7204/api/%s/", controllerName);
    }
    public static String getCurrentUsername() {
        return "test1";
    }


}
