package com.example.patientsocialdistance.pojo.DTOs;

public class VisitorRequestVisitDTO {
    public VisitorRequestVisitDTO(int visitId, String visitorName, String visitDate, String visitMessage, int durationInMinutes, Boolean isStartDateChange, String newDate) {
        this.visitId = visitId;
        this.visitorName = visitorName;
        this.visitDate = visitDate;
        this.visitMessage = visitMessage;
        this.durationInMinutes = durationInMinutes;
        this.isStartDateChange = isStartDateChange;
        this.newDate = newDate;
    }

    public int visitId;
    public String visitorName;
    public String visitDate;
    public String visitMessage;
    public int durationInMinutes;

    public void setStartDateChange(Boolean startDateChange) {
        isStartDateChange = startDateChange;
    }

    public Boolean isStartDateChange;

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String newDate;
}
