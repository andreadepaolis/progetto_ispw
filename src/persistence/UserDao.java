package persistence;

import model.Assenze;
import model.Grades;
import model.User;
import query.UserQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class UserDao {

    public static User validate(int matricola, String password) throws SQLException {


            DataBase db = DataBase.getInstance();
            Connection con = db.getConnection();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs= UserQuery.login(stmt,matricola,password);


            if(rs.first()){
                rs.first();
                //1 nome 2 lastname 3 matricola 4 classe
                User u = new User(rs.getString("name"),rs.getString("lastname"),rs.getInt("matricola"),rs.getString("class"));

                return u;
            }

            else {
                return null;

            }

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {


            try {
                db.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public static User GetUserById(int id) {

        DataBase db = DataBase.getInstance();
        Connection con = db.getConnection();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs= UserQuery.getById(stmt,id);
            if (rs == null || !rs.first()) {
                return null;

            } else

            rs.first();
                //1 nome 2 lastname 3 matricola 4 classe
                return new User(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getString(5));


        }catch(Exception e) {
            e.printStackTrace();

            return null;
        } finally {

            try {
                db.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static List<Grades> getMyGrades(int id){
        Statement stmt = null;
        Connection con = null;
        List<Grades> allGrades = new ArrayList<Grades>();

        try {

            DataBase db =  DataBase.getInstance();
            con = db.getConnection();

            stmt = con.createStatement();
            ResultSet rs = UserQuery.getGrades(stmt, id);


            if (!rs.first()){
                return null;

            }

            // riposizionamento del cursore
            rs.first();
            do{
                System.out.println(rs.getString("materia"));
                String materia = rs.getString("materia");
                int voto = rs.getInt("voto");
                String professor = rs.getString("nomeProfessore");
                String tipo = rs.getString("tipo");
                Date data = rs.getDate("data");

                Grades g = new Grades(materia, voto, tipo, professor, data);

                allGrades.add(g);

            }while(rs.next());

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allGrades;
     }

    public static List<Assenze> getMyAssenze(int id) {

        Statement stmt = null;
        Connection con = null;
        List<Assenze> allAssenze = new ArrayList<>();

        try {

            DataBase db =  DataBase.getInstance();
            con = db.getConnection();

            stmt = con.createStatement();
            ResultSet rs = UserQuery.getAssenze(stmt, id);


            if (rs == null || !rs.first()) {
                return null;

            }

            // riposizionamento del cursore
            rs.first();
            do{

                String tipo = rs.getString("tipo");
                int checkbit = rs.getInt("checkbit");
                Date data = rs.getDate("data");

                Assenze a = new Assenze(id,tipo,data,checkbit);

                allAssenze.add(a);

            }while(rs.next());

            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // STEP 5.2: Clean-up dell'ambiente

        return allAssenze;
    }
}
