package query;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserQuery {

    public static ResultSet login(Statement stmt, int matricola, String password) throws SQLException  {
        String sql = String.format("SELECT * FROM professor where  matricola ='%d' AND password = '%s",matricola,password);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ResultSet getById(Statement stmt, int userid) throws SQLException  {
        String sql = String.format("SELECT * FROM users where matricola =%s",userid);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getGrades(Statement stmt, int id) {
        System.out.println(id);
        String sql = String.format("SELECT * FROM grades where matricolaStudente =%d",id);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAssenze(Statement stmt, int id) {
        String sql = String.format("SELECT * FROM assenza WHERE matricolaStudente =%d",id);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
