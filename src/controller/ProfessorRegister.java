package controller;

import model.Assenze;
import model.Grades;
import model.Professor;
import model.User;
import persistence.ProfessorDao;
import persistence.UserDao;

import java.util.ArrayList;
import java.util.List;

public class ProfessorRegister implements Register {

    private List<Grades> grades;
    private List<Assenze> assenze;
    private List<User> users;

    public ProfessorRegister(){

    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public List<Assenze> getAssenze() {
        return assenze;
    }

    public void setAssenze(List<Assenze> assenze) {
        this.assenze = assenze;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public List<Grades> getMyGrades(int id) {
        List<Grades> result;
        result = UserDao.getMyGrades(id);
        return result;

    }
    @Override
    public List<Assenze> getAssenze(int id) {
        List<Assenze> result;
        result = UserDao.getMyAssenze(id);
        return result;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public List<User> getAllUserForClass(String c) {
        List<User> users;
        users = ProfessorDao.getClasse(c);
        return users;
    }

}
