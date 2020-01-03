package controller;

import model.Assenze;
import model.Grades;
import model.User;

import java.util.List;

public interface Register {


    List<Grades> getMyGrades(int id);

    List<Assenze> getAssenze(int id);

    User getUser();

    List<User>getAllUserForClass(String c);

  //  List<Grades> sortBy()

//many sorting algoritms for user
}
