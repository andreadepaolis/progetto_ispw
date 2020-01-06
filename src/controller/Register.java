package controller;

import factory.month;
import model.Assenze;
import model.Grades;
import model.User;

import java.util.Date;
import java.util.List;

public interface Register {


    List<Grades> getMyGrades(int id);

    List<Assenze> getAssenze(int id);

    User getUser();

    List<User>getAllUserForClass(String c);

    List<Grades> getMyGrades(int id, month m,String materia);

    List<Assenze> getAssenze(int id, month m);
  //  List<Grades> sortBy()

//many sorting algoritms for user
}
