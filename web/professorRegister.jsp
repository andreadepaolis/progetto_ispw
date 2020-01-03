<%@ page import="controller.ProfessorRegister" %>
<%@ page import="model.Professor" %>
<%@ page import="model.User" %>
<%@ page import="model.Grades" %>
<jsp:include page="ProfessorRegisterServlet" />

<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 02/01/2020
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Register</title>
</head>
<body>

HELLO WECOLME IN REGISTER
<form action="ProfessorRegisterServlet" method="get">

<title>Register</title>
<%

    if(session.getAttribute("professor") == null){
        response.sendRedirect("login.html");
    }
    Professor p  = (Professor) session.getAttribute("professor");
    if(request.getAttribute("register")!= null ){
        ProfessorRegister register = (ProfessorRegister)request.getAttribute("register");

        for(User u: register.getUsers()){

%>          <br>
<%=u.getName()%> <%=u.getLastname()%>


<%       if(u.getGrades() != null){
            for(Grades g: u.getGrades()){


%>
        <%=g.getMateria()%>         <%=g.getVoto()%>

<%      }
      }
    }
}
%>
</form>


</body>
</html>
