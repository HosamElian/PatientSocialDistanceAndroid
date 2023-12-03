package com.example.patientsocialdistance.pojo.DTOs;

public class GetVisitsRequest {
    public GetVisitsRequest(String username, Boolean isApproved) {
        this.username = username;
        this.isApproved = isApproved;
    }

    public String username;
    public Boolean isApproved;
}
