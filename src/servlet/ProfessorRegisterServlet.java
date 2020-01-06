package servlet;

import controller.ProfessorRegister;
import factory.MonthFactory;
import factory.month;
import model.Assenze;
import model.Grades;
import model.Professor;
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
import java.util.*;

@WebServlet("/ProfessorRegisterServlet")
public class ProfessorRegisterServlet extends HttpServlet {

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false);
        String cmd = request.getParameter("cmd");
        System.out.println(cmd);
        if(cmd.equals("ng")){
            Professor p = (Professor) session.getAttribute("professor");
            int voto = Integer.parseInt(request.getParameter("voto"));
            String tipo =request.getParameter("tipo");
            String materia = request.getParameter("materia");
            int matricola = Integer.parseInt(request.getParameter("matricola"));
            int matricolaProfessore = p.getMatricola();
            String nomeProfessore = p.getLastname();
            Date d = new Date();
            Grades g = new Grades(matricola,materia,voto,tipo,matricolaProfessore,nomeProfessore,d );
            int result = ProfessorDao.saveGrades(g);
            if(result > 0){
                request.setAttribute("title","ok");
                request.setAttribute("message","Salvato Correttamente");

            } else {
                request.setAttribute("title", "error");
                request.setAttribute("message", "Controlla i dati inseriti e riprova");
            }

        }
        if (cmd.equals("a")) {
            session.setAttribute("classe","4B");
        }
       else if(cmd.equals("b")){
            session.setAttribute("classe","3B");
        }
        if(cmd.equals("m")){
                    //temp contiene +1 o -1
            int value = Integer.parseInt(request.getParameter("monthIndex"));
            System.out.println(value);
                 MonthFactory mf = new MonthFactory();
                month m = mf.createMonth(value);
                System.out.println(m.getName());
                request.setAttribute("month",m);
        }
        if(cmd.equals("mat")){
            String materia = request.getParameter("materia");
            request.setAttribute("materia",materia);
            System.out.println(materia);

        }
            doGet(request,response);
    }

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession(false);
            if(session.getAttribute("professor") == null){
                return;
            } else {
                 Professor p = (Professor) session.getAttribute("professor");
                 ProfessorRegister register = new ProfessorRegister();
                 String classe;
                if(session.getAttribute("classe") != null) {
                    classe = session.getAttribute("classe").toString();
                } else {
                    List<String> a = ProfessorDao.getClassi(p.getMatricola());
                    classe = a.get(0);
                    session.setAttribute("classe", classe);
                }
                List<User> allUserForClass = register.getAllUserForClass(classe);
                allUserForClass.sort(new Comparator<>() {
                    public int compare(User s1, User s2) {
                        return s1.getLastname().compareToIgnoreCase(s2.getLastname());
                    }
                });
                register.setUsers(allUserForClass);

                Date d = new Date();
                month m;
                if(request.getAttribute("month") == null) {
                    Calendar cal = GregorianCalendar.getInstance();
                    cal.setTime(d);
                    MonthFactory f = new MonthFactory();
                     m = f.createMonth(cal.get(Calendar.MONTH)+1);
                    request.setAttribute("month",m);
                } else {
                     m = (month)request.getAttribute("month");
                    System.out.println(m.getName());

                }
                String materia;
                if(request.getAttribute("materia") != null){
                     materia = request.getAttribute("materia").toString();
                } else
                    materia = p.getMateria().get(0);

                for(User u: allUserForClass){
                    List<Grades> temp = register.getMyGrades(u.getMatricola(),m,materia);
                    List<Assenze> temp2 = register.getAssenze(u.getMatricola(),m);

                    if(temp != null) {
                        List<Grades> grades = new ArrayList<>(temp);
                        u.setGrades(grades);
                    }
                    if(temp2 != null){
                        List<Assenze> assenze = new ArrayList<>(temp2);
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
