package com.example.patientsocialdistance.pojo.DTOs;

public class VisitorDto {
    public VisitorDto(int visitId, String visitorName, String visitDate, String visitMessage) {
        this.visitId = visitId;
        this.visitorName = visitorName;
        this.visitDate = visitDate;
        this.visitMessage = visitMessage;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitMessage() {
        return visitMessage;
    }

    public void setVisitMessage(String visitMessage) {
        this.visitMessage = visitMessage;
    }

    public int visitId;
    public String visitorName;
    public String visitDate;
    public String visitMessage;
}
