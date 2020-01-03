package persistence;
import java.sql.*;

public class DataBase {

    private String url = "jdbc:mysql://localhost:3306/project6";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String userName = "root";
    private String password = "password";
    private static DataBase db;
    private static Connection con ;
    private static Statement stmt;


    private DataBase() {
        // private constructor //
    }

    public static DataBase getInstance(){
        if(db==null){
            db= new DataBase();
        }
        return db;
    }

    public  Connection getConnection() {

        try {
            Class.forName(driver);
            if (this.con == null) {
                this.con = DriverManager.getConnection(url, userName, password);
            }
            return this.con;
        } catch (Exception e) {
            System.out.print("Error : " + e.getMessage());
            return this.con;
        }
    }

    public void closeConnection(Connection connect) throws SQLException {

        if(connect != null){
            try {
                connect.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }


}



