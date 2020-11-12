package com.example.projetoids;

public class User {
    private String name;
    private String birthDate;
    private String email;

    public User(String name,String email,String birthDate){
        this.name=name;
        this.email=email;
        this.birthDate=birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
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
