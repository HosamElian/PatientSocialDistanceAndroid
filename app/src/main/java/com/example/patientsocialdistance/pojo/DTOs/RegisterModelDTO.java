package com.example.patientsocialdistance.pojo.DTOs;

public class RegisterModelDTO {

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int userTypeId ;
    public String password ;
    public int age ;
    public String name ;
    public String email ;
    public String username ;
    public String nationalId ;
    public String nationality ;
    public String hospital ;

    public RegisterModelDTO(int userTypeId, String password, int age, String name, String email, String username, String nationalId, String nationality, String hospital) {
        this.userTypeId = userTypeId;
        this.password = password;
        this.age = age;
        this.name = name;
        this.email = email;
        this.username = username;
        this.nationalId = nationalId;
        this.nationality = nationality;
        this.hospital = hospital;
    }
}
