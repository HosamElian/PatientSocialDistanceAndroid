package com.example.patientsocialdistance.pojo.DTOs;

public class TokenRequestModel {
    public TokenRequestModel(String email, String password) {
        Email = email;
        Password = password;
    }

    public String Email;
    public String Password;
}
