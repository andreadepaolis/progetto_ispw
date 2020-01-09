package servlet;

import model.Homework;
import model.Professor;
import model.ScheduleInfo;
import model.User;
import persistence.ProfessorDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/HomeProfessorServlet")
public class HomeProfessorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/homeProfessor.jsp");

        try{
            HttpSession session = request.getSession(false);
            if(session.getAttribute("professor") == null) {
                System.out.println("eccpetion");
                throw new Exception();
            }
            String cmd = request.getParameter("cmd");
            System.out.println(cmd);

            if(cmd.equals("newhw")) {

                Professor p = (Professor) session.getAttribute("professor");
                String classe = request.getParameter("classe");
                String materia = request.getParameter("materia");
                String data = request.getParameter("data");
                String description = request.getParameter("descrizione");
                if(data == null  || data.equals("")){
                    request.setAttribute("title","Error");
                    request.setAttribute("message", "Check parameter");
                    rd.forward(request, response);
                    return;
                }
            try {
                data = data.replace('-','/');
               Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(data);

                int result = ProfessorDao.newHomework(p.getMatricola(), classe, materia, date1, description);

                if (result > 0) {
                    request.setAttribute("title", "ok");
                    request.setAttribute("message", "new homework correctly saved");
                } else {
                    request.setAttribute("title","Error");
                    request.setAttribute("message", "Try Again");

                }
                rd.forward(request, response);

    } catch (ParseException e){
        e.printStackTrace();
        request.setAttribute("title","Error");
        request.setAttribute("message", "invalid Date");
        rd.forward(request, response);

    }
            } else if(cmd.equals("reg")) {
                response.sendRedirect("professorRegister.jsp");
            }


        } catch (Exception e){
            e.printStackTrace();
            rd.include(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            HttpSession session = request.getSession(false);
            if(session.getAttribute("professor") == null){
                throw new Exception();

            } else {
                Professor p = (Professor)session.getAttribute("professor");
                //List<User> list = ProfessorDao.getClasse("4B");
                List<Homework> homeworks = ProfessorDao.getHomework(p.getMatricola(),new Date());
                p.setHomework(homeworks);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/homeProfessor.jsp");
                rd.forward(request, response);

            }

        } catch (Exception e){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/homeProfessor.jsp");
            PrintWriter out = response.getWriter();
            out.println(e);
            rd.include(request, response);

        }

    }
}
