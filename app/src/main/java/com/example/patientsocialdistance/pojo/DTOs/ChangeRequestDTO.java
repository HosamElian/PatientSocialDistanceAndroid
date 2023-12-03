package com.example.patientsocialdistance.pojo.DTOs;

public class ChangeRequestDTO {
    public ChangeRequestDTO(int visitId, int notificationId, Boolean isAccepted) {
        this.visitId = visitId;
        this.notificationId = notificationId;
        this.isAccepted = isAccepted;
    }

    public int visitId;
    public int notificationId;
    public Boolean isAccepted;
}
