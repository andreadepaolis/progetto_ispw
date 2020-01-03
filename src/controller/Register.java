package controller;

import model.Assenze;
import model.Grades;
import model.User;

import java.util.List;

public interface Register {


    List<Grades> getGrades(int i,String s);

    List<Assenze> getAssenze(String c);

    User getUser();

     List<User>getAllUserForClass(String c);

//many sorting algoritms for user
}
