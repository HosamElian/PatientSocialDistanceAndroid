package com.example.patientsocialdistance.pojo.DTOs;

public class VisitApprovalDTO {

    public VisitApprovalDTO(int id, int statusId, int durationInMinutes, Boolean isStartDateChange, String newDate) {
        this.id = id;
        this.statusId = statusId;
        this.durationInMinutes = durationInMinutes;
        this.isStartDateChange = isStartDateChange;
        this.newDate = newDate;
    }

    public int id;
    public int statusId;
    public int durationInMinutes;
    public Boolean isStartDateChange;
    public String newDate;
}
