package controller;

import factory.month;
import model.Assenze;
import model.Grades;
import model.User;
import persistence.ProfessorDao;
import persistence.UserDao;
import utils.registerUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProfessorRegister extends registerUtils implements Register {

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
    @Override
    public List<Grades> getMyGrades(int id, month m,String materia){
        List<Grades> result = new ArrayList<>();
        List<Grades> temp = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.set(m.getYear(),m.getIndex()-1,0);
        Calendar end = Calendar.getInstance();
        end.set(m.getYear(),m.getIndex()-1,m.getDay());
        temp  = UserDao.getMyGrades(id);
        if(temp != null) {
            for (Grades g : temp) {
                if (g.getData().before(end.getTime()) && start.getTime().before(g.getData())&& g.getMateria().equals(materia)){
                    result.add(g);
                }

            }
            return result;
        }
        return null;
    }
    @Override
    public List<Assenze> getAssenze(int id, month m){
        List<Assenze> result = new ArrayList<>();
        List<Assenze> temp = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.set(m.getYear(),m.getIndex()-1,0);
        Calendar end = Calendar.getInstance();
        end.set(m.getYear(),m.getIndex()-1,m.getDay());
        temp  = UserDao.getMyAssenze(id);
        if(temp != null) {
            for (Assenze a : temp) {
                if (a.getData().before(end.getTime()) && start.getTime().before(a.getData()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }

    public int newGrades(int ms,String name, String materia,int voto, String tipo,int professorid,String professor,Date data){
        Grades g = new Grades(ms,materia,voto,tipo,professorid,professor,data);
        return ProfessorDao.saveGrades(g);
    }

    public double getMedia(int matricola,String materia) {

         double media = 0;
         List<Grades> voti = ProfessorDao.getMedia(matricola,materia);
         int count = 0;
         if(voti == null){
             return 0;
         }
         for(Grades g : voti){
             count++;
             media += g.getVoto();
         }
         return Math.round((media*10/count))/10.0;

    }
}
