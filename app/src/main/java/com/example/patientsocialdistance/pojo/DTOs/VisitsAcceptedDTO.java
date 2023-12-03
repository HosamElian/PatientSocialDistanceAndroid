package com.example.patientsocialdistance.pojo.DTOs;

public class VisitsAcceptedDTO {
    public String getVisitedUsername() {
        return visitedUsername;
    }

    public void setVisitedUsername(String visitedUsername) {
        this.visitedUsername = visitedUsername;
    }

    public String getVisitorUsername() {
        return visitorUsername;
    }

    public void setVisitorUsername(String visitorUsername) {
        this.visitorUsername = visitorUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String visitedUsername;
    public String visitorUsername;
    public String message;
    public String startDate;
    public String startTime;
    public int durationInMinutes;
}
