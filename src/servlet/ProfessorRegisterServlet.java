package servlet;

import controller.ProfessorRegister;
import model.Assenze;
import model.Grades;
import model.Professor;
import model.User;
import persistence.ProfessorDao;
import persistence.UserDao;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HEELLO GET");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

                for(User u: allUserForClass){
                    List<Grades> temp = UserDao.getMyGrades(u.getMatricola());
                    if(temp != null) {
                        for (Grades s : temp) {
                            grades.add(s);
                        }
                        u.setGrades(grades);

                    }
                }

           //     List<Assenze> assenze = register.getAssenze("3B");
              //  register.setAssenze(assenze);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/professorRegister.jsp");
                request.setAttribute("register",register);

                rd.forward(request, response);
            }

        } catch (Exception e){
           e.printStackTrace();

        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/professorRegister.jsp");
        rd.forward(request, response);


    }
}
