package query;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserQuery {

    public static ResultSet login(Statement stmt, String matricola, String password) throws SQLException  {
        String sql = "SELECT * FROM users where matricola = matricola AND password = password";
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
}
