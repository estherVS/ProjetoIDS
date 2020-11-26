package com.example.projetoids.Classes;

public class User {
    private String profileUrl;
    private final String name;
    private final String birthDate;
    private final String email;

    public User(String profileUrl,String name,String birthDate,String email){
        this.profileUrl=profileUrl;
        this.name=name;
        this.birthDate=birthDate;
        this.email=email;
    }

    public String getProfileUrl(){
        return profileUrl;
    }
    public String getName(){
        return name;
    }
    public String getBirthDate(){
        return birthDate;
    }
    public String getEmail(){
        return email;
    }
}
