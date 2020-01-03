package servlet;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try{
                HttpSession session = request.getSession(false);
                if(session.getAttribute("user") == null){
                    throw new Exception();
                } else {
                    User u = (User)request.getAttribute("user");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                        request.setAttribute("user",u);
                        rd.forward(request, response);
                }

            } catch (Exception e){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
                PrintWriter out = response.getWriter();
                out.println(e);
                rd.include(request, response);

            }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HELLOGET");
    }
}
