package query;

import model.Assenze;
import model.Homework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class ProfessorQuery {
    public static ResultSet login(Statement stmt, int matricola, String password) {
        String sql = String.format("SELECT * FROM professor WHERE matricola ='%d' AND password = '%s'",matricola,password);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getStudentsOfClass(Statement stmt, String classe) {
        String sql = String.format("SELECT * FROM users WHERE class = '%s'" ,classe);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet getThisWeekHomework(Statement stmt, int professorId, Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE,+7);
        Date end = c.getTime();
        c.add(Calendar.DATE,-14);
        Date start = c.getTime();

        String sql = "SELECT * FROM Homework where matricolaProfessor = professorId AND date beetween start and end";
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static int saveNewHomework(Statement stmt, Homework h) {

            int mp = h.getMatricolaProfessore();
            String c = h.getClasse();
            String m = h.getMateria();
            String d = h.getDescription();
            Date data = h.getData();
            String sql = String.format("INSERT INTO homework(matricolaProfessore,class,materia,descrizione,data) VALUES('%d','%s','%s','%s','%tD')",mp,c,m,d,data);

        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet getGradesByClass(Statement stmt,int professorid ,String classe) {

       // String sql = String.format("SELECT * FROM grades WHERE matricolaProfessore = '%s' INNER JOIN users ON users.class = '%s'",professorid,classe);
        String sql = String.format("SELECT * FROM grades WHERE matricolaProfessore = '%s'",professorid);

        System.out.println(sql);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("rip");
            return null;
        }
    }

    public static int saveNewAssenza(Statement stmt, Assenze a) {
        int ms = a.getMatricolaStudente();
        String type = a.getTipo();
        Date data = a.getData();
        String sql = String.format("INSERT INTO assenze(matricolaStudente,data,tipo,checkbit) VALUES('%d','%tD','%s','%d')",ms,data,type,1);

        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
