package model;

import java.util.List;

public class Professor {

         private String name;
         private String lastname;
         private int matricola;
         private List <String> materia;
         private List<String> classi;
         private List<User> studentList;
         private List<Homework> homework;

         public Professor(String name, String lastname,int matricola){
             this.name = name;
             this.lastname = lastname;
             this.matricola = matricola;
         }
         public Professor(String name, String lastname,int matricola,List<String> materia,List<String> classi){
                this.name = name;
                this.lastname = lastname;
                this.matricola = matricola;
                this.materia = materia;
                this.classi = classi;
            }


    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public List<String> getClassi() {
        return classi;
    }

    public void setClassi(List<String> classe) {
        this.classi = classi;
    }


    public List<Homework> getHomework() {
        return homework;
    }

    public void setHomework(List<Homework> homework) {
        this.homework = homework;
    }

    public List<String> getMateria() {
        return materia;
    }

    public void setMateria(List<String> materia) {
        this.materia = materia;
    }
}
