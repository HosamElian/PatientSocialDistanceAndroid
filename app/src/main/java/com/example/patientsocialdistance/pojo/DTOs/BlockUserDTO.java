package com.example.patientsocialdistance.pojo.DTOs;

public class BlockUserDTO {
    public BlockUserDTO(String usernameMakeBlock, String usernameBlocked, Boolean makeBlock, Boolean hasNotification) {
        this.usernameMakeBlock = usernameMakeBlock;
        this.usernameBlocked = usernameBlocked;
        this.makeBlock = makeBlock;
        this.hasNotification = hasNotification;
    }

    public String usernameMakeBlock;
    public String usernameBlocked;
    public Boolean makeBlock;
    public Boolean hasNotification;
}
