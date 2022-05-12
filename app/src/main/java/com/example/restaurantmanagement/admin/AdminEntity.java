package com.example.restaurantmanagement.admin;


public class AdminEntity {

    private String person_name;
    private String status;
    private String position;
    private String username;
    private String password;

    // Constructor
    public AdminEntity(String person_name, String status, String position, String username, String password) {
        this.person_name = person_name;
        this.status = status;
        this.position = position;
        this.username = username;
        this.password = password;
    }

    // Getter and Setter

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}