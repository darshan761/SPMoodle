package com.example.hp.moodle;

/**
 * Created by HP on 26-08-2018.
 */

public class User {
    public String name;
    public String username;
    public String Email;
    public String Type;

    User(){

    }

    public User(String name, String username, String email, String type) {
        this.name = name;
        this.username = username;
        Email = email;
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
