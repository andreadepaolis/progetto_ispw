package query;

import model.Assenze;
import model.Grades;
import model.Homework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfessorQuery {
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static ResultSet login(Statement stmt, int matricola, String password) {
        String sql = String.format("SELECT * FROM professor WHERE matricola ='%d' AND password = '%s'", matricola, password);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getStudentsOfClass(Statement stmt, String classe) {
        String sql = String.format("SELECT * FROM users WHERE class = '%s'", classe);
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
        c.add(Calendar.DATE, +7);
        Date end = c.getTime();
        c.add(Calendar.DATE, -14);
        Date start = c.getTime();

        String sql = String.format("SELECT * FROM Homework where matricolaProfessore = '%d' AND date beetween '%tD' and '%tD'", professorId, start, end);
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
        String sql = String.format("INSERT INTO homework(matricolaProfessore,class,materia,descrizione,data) VALUES('%d','%s','%s','%s','%tD')", mp, c, m, d, data);

        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet getGradesByClass(Statement stmt, int professorid, String classe) {

        // String sql = String.format("SELECT * FROM grades WHERE matricolaProfessore = '%s' INNER JOIN users ON users.class = '%s'",professorid,classe);
        String sql = String.format("SELECT * FROM grades WHERE matricolaProfessore = '%s'", professorid);

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
        String sql = String.format("INSERT INTO assenze(matricolaStudente,data,tipo,checkbit) VALUES('%d','%tD','%s','%d')", ms, data, type, 1);

        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int saveNewGrades(Statement stmt, Grades g) {

        int ms = g.getMatricolaStudente();
        String nameP = g.getNomeProfessore();
        String tipo = g.getTipo();
        int voto = g.getVoto();
        int pfid = g.getProfessorid();
        Date d = g.getData();
        String materia = g.getMateria();
        String sql = String.format("INSERT INTO Grades(matricolaStudente,matricolaProfessore,nomeProfessore,materia,voto,tipo,data) VALUES('%d','%d','%s','%s','%d','%s','%tF')", ms, pfid, nameP, materia, voto, tipo, d);

        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet getClassi(Statement stmt, int professorid) {

        String sql = String.format("SELECT * FROM classi WHERE matricolaProfessore = '%s'", professorid);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getMaterie(Statement stmt, int matricola) {
        String sql = String.format("SELECT * FROM materia WHERE matricolaProfessore = '%s'", matricola);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getUserGradesForMateria(Statement stmt, int matricola, String materia) {
        String sql = String.format("SELECT * FROM grades WHERE matricolaStudente = '%s' AND materia = '%s'", matricola, materia);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getScheduleForProfessor(Statement stmt, int professorid) {
        String sql = String.format("SELECT * FROM scheduleinfo WHERE matricolaProfessore = '%s'",professorid);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
