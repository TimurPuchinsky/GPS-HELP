package com.example.gpshelp;

public class User {
    private String surname, name, fname, birthdate, passport, snils, liveplace, workplace, email, password;

    public User(){}

    public User(String surname, String name, String fname, String birthdate, String passport,
                String snils, String liveplace, String workplace, String email, String password) {
        this.surname = surname;
        this.name = name;
        this.fname = fname;
        this.birthdate = birthdate;
        this.passport = passport;
        this.snils = snils;
        this.liveplace = liveplace;
        this.workplace = workplace;
        this.email = email;
        this.password = password;

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getLiveplace() {
        return liveplace;
    }

    public void setLiveplace(String liveplace) {
        this.liveplace = liveplace;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

