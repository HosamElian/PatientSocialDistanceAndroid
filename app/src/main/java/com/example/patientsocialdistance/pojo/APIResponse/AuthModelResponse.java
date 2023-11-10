package com.example.patientsocialdistance.pojo.APIResponse;

import java.util.List;

public class AuthModelResponse {
    public Boolean isAuthenticated ;
    public String email ;
    public String username ;
    public String message ;
    public String token ;
    public String expiresOn ;
    public List<String> roles ;
}
