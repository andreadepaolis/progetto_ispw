<%@ page import="model.Professor" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/12/2019
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link rel="stylesheet" href="css/login.css" type="text/css">
    <link rel="stylesheet" href="css/toast.css" type="text/css">


    <title>Home</title>

</head>
<body class="roma">
<%

    //allow access only if session exists
    if(session.getAttribute("professor") == null){
        response.sendRedirect("login.html");
    }
    Professor p  = (Professor) session.getAttribute("professor");
    String name = p.getName();
%>
<h3>Hi <%=name %>, do the checkout.</h3>
<br>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout" >
</form>
<%

%>
<form action="HomeProfessorServlet" method="post">
    <input type ="hidden" name="cmd" value="reg">
    <input type="submit" value="register">
</form>

<form action="HomeProfessorServlet" method="post">

    Materia <input type="text" class="text" name="materia">
    <br>
    Description<input type="text" style = "width: 400px; height: 150px" name="descrizione">
    <br>
    Classe <input type="text" name="classe">
    <br>
    Data <input type="text" name="data">
    <input type ="hidden" name="cmd" value="newhw">
    <input type="submit" value="save">
</form>
<!-- The actual snackbar -->

<div id="snackbar">${title}<br>${message}</div>
<script type="text/javascript">
    function myFunction(value) {
        var x = document.getElementById("snackbar");
        console.log(value);
        if(value == "1"){
            x.className = "succ";
            setTimeout(function(){ x.className = x.className.replace("succ", ""); }, 3000);
        } else {
            x.className = "error";
            setTimeout(function () {
                x.className = x.className.replace("succ", "");
            }, 3000);
        }

    }
</script>


    <%
        if(request.getAttribute("title")!= null ){
            String title = request.getAttribute("title").toString();
            if(title.equals("ok")) {
    %>
<script type="text/javascript">myFunction("1");</script>
<%
        } else {
%>
<script type="text/javascript">myFunction("2");</script>

<%
            }
    }
%>



</body>
</html>
