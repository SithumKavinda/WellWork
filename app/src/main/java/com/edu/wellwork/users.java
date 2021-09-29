package com.edu.wellwork;

public class users {
    String id;
    String name;
    String contact;
    String Email;
    String password;

    public users() {
    }

    public users(String name, String contact, String email) {
        this.name = name;
        this.contact = contact;
        this.Email = email;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }
}
