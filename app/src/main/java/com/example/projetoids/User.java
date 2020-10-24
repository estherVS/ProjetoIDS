package com.example.projetoids;

public class User {
    private final String name;
    private final String birthDate;
    private final String email;

    public User(String name,String email,String birthDate){
        this.name=name;
        this.email=email;
        this.birthDate=birthDate;
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
