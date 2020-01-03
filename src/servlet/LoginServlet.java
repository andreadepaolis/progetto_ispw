package servlet;

import model.Professor;
import model.User;
import persistence.ProfessorDao;
import persistence.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String matricola = request.getParameter("matricola");
        String password = request.getParameter("password");
                //check and return
        String cmd = request.getParameter("type");
        if(cmd.equals("user")){
        try {
            User user = UserDao.validate(matricola, password);
            if (user != null) {
                user.setGrades(UserDao.getMyGrades(user.getMatricola()));
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30 * 60);

                response.sendRedirect("home.jsp");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font>Either user name or password is wrong</font>");
                rd.include(request, response);
            }
        } catch (SQLException se){
            System.out.println(se);
        }

    } else if (cmd.equals("professor")) {
            Professor p = null;
            try {
                p = ProfessorDao.validate(matricola,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (p != null) {
                HttpSession session = request.getSession();
                session.setAttribute("professor", p);
                session.setMaxInactiveInterval(30 * 60);
                response.sendRedirect("homeProfessor.jsp");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font>Either user name or password is wrong</font>");
                rd.include(request, response);
             }
            } else {

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            rd.include(request, response);

        }
    }

}