package com.example.restaurantmanagement.admin.Entity;


public class UserObject {

    private int id;
    private String person_name;
    private String status;
    private String position;
    private String username;
    private String password;

    // Constructor
    public UserObject(int id, String person_name, String status, String position, String username, String password) {
        this.id = id;
        this.person_name = person_name;
        this.status = status;
        this.position = position;
        this.username = username;
        this.password = password;
    }

    // Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getRole() {
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