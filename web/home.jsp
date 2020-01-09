<%@ page import="model.User" %>
<%@ page import="model.Grades" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/12/2019
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<h3> Login successful</h3>

<br>

</form>
</body>

<form action="HomeServlet" method="get">

    <%
        User user = null;
        if(session.getAttribute("user") == null)
            response.sendRedirect("login.jsp");
        else {
            user = (User)session.getAttribute("user");

    %>
</form>
Hi my friend <%=user.getName()%>
<%
    if(user.getGrades()!= null){
        for(Grades temp: user.getGrades()){


%>

Matieria: <%=temp.getMateria()%>
voto: <%=temp.getVoto()%>
Professore: <%=temp.getProfessor()%>
Data: <%=temp.getData()%>


<%


        }

        }
    }
%>



</html>
