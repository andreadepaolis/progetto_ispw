package model;

import java.util.List;

public class User {

    private int matricola;
    private String password;
    private String name;
    private String lastname;
    private String classe;
    private List<Grades> grades;

    public User(String name,String lastname,int matricola,String classe){
        this.name = name;
        this.matricola = matricola;
        this.lastname = lastname;
        this.classe = classe;
    }

    public String getName() {
        return name;
    }

    public int getMatricola() {
        return matricola;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }
}
