package com.example.patientsocialdistance.pojo.DTOs;

public class VisitDto {
    public VisitDto(String VisitedUsername, String VisitorUsername, String visitDate, String message) {
        this.visitedUsername = VisitedUsername;
        this.visitorUsername = VisitorUsername;
        this.visitDate = visitDate;
//        this.reason = reason;
        this.message = message;
    }

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

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String visitedUsername;
    public String visitorUsername;
    public String visitDate;
//    public String reason;
    public String message;
}
