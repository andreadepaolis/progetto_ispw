<%@ page import="model.Professor" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.lang.model.type.ArrayType" %>
<%@ page import="java.util.Vector" %>
<%@ page import="model.ScheduleInfo" %>
<%@ page import="persistence.ProfessorDao" %><%--
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
    <link rel="stylesheet" href="css/app.css" type="text/css">
    <link rel="stylesheet" href="css/navbar.css" type="text/css">
    <link rel="stylesheet" href="css/home.css" type="text/css">
    <link rel="stylesheet" href="css/register.css" type="text/css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Home</title>
</head>
<body>
<ul>
    <li><a href="homeProfessor.jsp">Home</a></li>
    <li><a href="professorRegister.jsp">Register</a></li>
    <li>
        <a>
        <form action="LogoutServlet" method="post">
            <input class="buttonLogout" type="submit" value="Logout">
        </form>
        </a>
    </li>
</ul>
<div class="container-fluid col-sm-12" style="padding:30px">
<%

    //allow access only if session exists
    if(session.getAttribute("professor") == null){
        response.sendRedirect("login.jsp");
    }
    Professor p  = (Professor) session.getAttribute("professor");
    String name = p.getName();
    String[] days = {"Lun","Mar","Mer","Gio","Ven","Sab"};
    int start = 9;
    String flag = "";
    List<ScheduleInfo> schedule = ProfessorDao.getSchedule(p.getMatricola());
%>
<div align="center" class="col-sm-12"><h3>Hi <%=name %>, welcome to your homepage.</h3></div>
<br>
<%

%>
<!--
<form action="HomeProfessorServlet" method="post">
    <input type ="hidden" name="cmd" value="reg">
    <input type="submit" value="register">
</form>
-->

<div class="container-fluid col-sm-12 row" style="margin:0px">
<div class="col-sm-6" align="center" style="border:0px">
<form action="HomeProfessorServlet" method="post">
<br>
    <div align="center" class="col-sm-12"><h5>Here below you can assign homeworks for your classes</h5></div><br>
    <div class="col-sm-12 row">
        <div class="col-sm-6">
        Select the subject:<br>
        <select name ="materia">
        <% for(String s: p.getMateria()){ %>
        <option value ="<%=s%>">
            <%=s%>
        </option>
        <% }%>
        </select>
        </div>
        <div class="col-sm-6">
            Select the class: <input type="text" name="classe">
        </div>
    </div>
    <br>
    Select the data:<br>
    <input type="date" name="data" value="20/01/2020">
    <input type ="hidden" name="cmd" value="newhw">
    <br><br>
    Homeworks:<br>
    <input type="text" style = "width: 400px; height: 150px" name="descrizione">
    <br><br>
    <div align="center"><input class="buttonSave" type="submit" value="save"></div>
</form>
</div>
    <div class="col-sm-6" align="center" style="border:0px">
        <br>
        <div align="center" class="col-sm-12"><h5>Schedule</h5></div><br>
        <table id="tg-7grMB" class="tg">
            <tr>
                <th>Orario</th>
                <%for(int i = 0; i < 6;i++){%>
                <th><%=days[i]%></th>
                <%}%>
            </tr>
            <%for(int j = 0; j < 6; j++){ %>
            <tr>
                 <td><%=start+j%>:00</td>
                <%for(int x = 0; x < 6; x++){
                    for(ScheduleInfo s: schedule) {
                        if (s.getDay() == x && s.getHours() == j + start)
                            flag = s.getMateria().concat(" ").concat(s.getClasse());

                    }%>
                        <td><%=flag%></td>
                    <%flag = "";%>
                <%}%>
            </tr>
            <%}%>

        </table>
    </div>




</div>
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
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
