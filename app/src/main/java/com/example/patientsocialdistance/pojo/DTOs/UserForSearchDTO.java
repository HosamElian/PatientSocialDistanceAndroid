package com.example.patientsocialdistance.pojo.DTOs;

public class UserForSearchDTO {
    public UserForSearchDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;
}
