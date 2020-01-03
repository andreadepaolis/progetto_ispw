package servlet;

import controller.ProfessorRegister;
import model.Assenze;
import model.Grades;
import model.Professor;
import model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProfessorRegisterServlet")
public class ProfessorRegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            HttpSession session = request.getSession(false);
            if(session.getAttribute("professor") == null){
                throw new Exception();
            } else {
                 Professor p = (Professor) request.getAttribute("professor");
                 //List<String> ProfessorClass = ProfessorDao.getNameOfClass(p.getMatricola());
                 ProfessorRegister register = new ProfessorRegister();


                List<User> allUserForClass = register.getAllUserForClass("3B");
                register.setUsers(allUserForClass);
                List<Grades> grades = new ArrayList<>();
                List<Assenze> assenze = new ArrayList<>();

                for(User u: allUserForClass){
                    List<Grades> temp = register.getMyGrades(u.getMatricola());
                    List<Assenze> temp2 = register.getAssenze(u.getMatricola());
                    if(temp != null) {
                        grades.addAll(temp);
                        u.setGrades(grades);

                    }
                    if(temp2 != null){
                        assenze.addAll(temp2);
                        u.setAssenze(assenze);
                    }
                }
                request.setAttribute("register",register);
            }

        } catch (Exception e){
           e.printStackTrace();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);

        }

    }
}
