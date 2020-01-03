package model;

import java.util.Date;

public class Grades {

        private String materia;
        private int voto;
        private String tipo;
     //   private Professor professor
       private String professor;
       private Date data;

       public Grades(String materia,int voto, String tipo,String professor,Date data){
           this.materia = materia;
           this.tipo = tipo;
           this.voto = voto;
           this.data = data;
           this.professor = professor;
       }

    public int getVoto() {
        return voto;
    }

    public String getMateria() {
        return materia;
    }

    public Date getData() {
        return data;
    }

    public String getProfessor() {
        return professor;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
