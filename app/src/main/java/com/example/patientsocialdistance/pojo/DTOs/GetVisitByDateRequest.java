package com.example.patientsocialdistance.pojo.DTOs;

public class GetVisitByDateRequest {

    public GetVisitByDateRequest(String username, Boolean isApproved, String date) {
        this.username = username;
        this.isApproved = isApproved;
        this.date = date;
    }

    public String username;
    public Boolean isApproved;
    public String  date;
}
