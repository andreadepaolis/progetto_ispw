package persistence;

import model.Grades;
import model.Homework;
import model.Professor;
import model.User;
import query.ProfessorQuery;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfessorDao {


    public static Professor validate(String matricola, String password) throws SQLException {


        Connection con = DataBase.getInstance().getConnection();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs= ProfessorQuery.login(stmt,matricola,password);

            if(rs.first()){
                //1 nome 2 lastname 3 matricola 4 classe
                Professor p = new Professor(rs.getString("name"),rs.getString("lastname"),rs.getInt("matricola"));

                return p;
            }

            else {
                throw new Exception("no user find");

            }

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<User> getClasse(String classe){

        Connection con = DataBase.getInstance().getConnection();

        List<User> list = new ArrayList<User>();
        try{
            Statement stmt = con.createStatement();

            ResultSet rs= ProfessorQuery.getStudentsOfClass(stmt,classe);
            if(!rs.first()){
                return null;
            }
            rs.first();
            do{
                User u = new User(rs.getString("name"),rs.getString("lastname"),rs.getInt("matricola"),classe);
              
                    list.add(u);
                
            } while(rs.next());
            rs.close();
            stmt.close();
              return list;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static List<Homework> getHomework(int professorId, Date date){

          List<Homework> list = new ArrayList<Homework>();
          Connection con = DataBase.getInstance().getConnection();
          try{
              Statement stmt = con.createStatement();

              ResultSet rs = ProfessorQuery.getThisWeekHomework(stmt,professorId,date);
              assert rs != null;
              rs.first();
              do{
                  Homework h = new Homework(rs.getInt("matricolaProfessore"),rs.getString("classe"),rs.getString("materia"),rs.getString("description"),rs.getDate("data"));
                  list.add(h);
              } while(rs.next());


          }catch(Exception e) {
              e.printStackTrace();
          }
          return list;
    }

    public static int newHomework(int professorid,String classe,String materia,Date data,String description){

    System.out.println(data);
            Homework h = new Homework(professorid,classe,description,materia,data);
            Connection con = DataBase.getInstance().getConnection();
            int result = 0;

        try {
                Statement stmt = con.createStatement();

                result = ProfessorQuery.saveNewHomework(stmt,h);


            } catch(Exception e) {
                e.printStackTrace();
            }
                return result;

    }




    public static List<Grades> getGradesForClass(int professorId,String classe) {

        List<Grades> list = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        try {

            DataBase db =  DataBase.getInstance();
            con = db.getConnection();

            stmt = con.createStatement();
            ResultSet rs = ProfessorQuery.getGradesByClass(stmt, professorId,classe);

            System.out.println("HELLO1");
            if (!rs.first()){
                return null;
            }

            // riposizionamento del cursore
            rs.first();

            do{
                String materia = rs.getString("materia");
                int voto = rs.getInt("voto");
                String professor = rs.getString("nomeProfessore");
                String tipo = rs.getString("tipo");
                Date data = rs.getDate("data");
                System.out.println("HELLO3");
                Grades g = new Grades(materia, voto, tipo, professor, data);

                list.add(g);

            }while(rs.next());

            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // STEP 5.2: Clean-up dell'ambiente
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


        return list;

    }

}
