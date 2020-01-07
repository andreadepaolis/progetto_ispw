<%@ page import="controller.ProfessorRegister" %>
<%@ page import="model.Professor" %>
<%@ page import="model.User" %>
<%@ page import="model.Grades" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="factory.month" %>
<%@ page import="factory.MonthFactory" %>
<%@ page import="model.Assenze" %>
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
    <link rel="stylesheet" href="css/app.css" type="text/css">
    <link rel="stylesheet" href="css/register.css" type="text/css">
    <link rel="stylesheet" href="css/navbar.css" type="text/css">
    <link rel="stylesheet" href="css/toast.css" type="text/css">


</head>
<body>

<div id="pop1">
    Helllo
</div>


<ul>
    <li><a href="homeProfessor.jsp">Home</a></li>
    <li><a href="professorRegister.jsp">Register</a></li>
    <li><a >
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout" >
        </form></a></li>
</ul>

HELLO WECOLME IN REGISTER


<%
    if(session.getAttribute("professor") == null){
        response.sendRedirect("login.html");
    }
    Professor p  = (Professor) session.getAttribute("professor");
    if(request.getAttribute("register")!= null ){
        ProfessorRegister register = (ProfessorRegister)request.getAttribute("register");

 if(session.getAttribute("classe")!= null){
    if(session.getAttribute("classe").toString().equals("3B")){%>

<form action="professorRegister.jsp" method="post">
    <input type="hidden" name="cmd" value="a">
    <input type="submit" value ="Go To 4B">
</form>
<% }else {%>
<form action="professorRegister.jsp" method="post">
<input type="hidden" name="cmd" value="b">
<input type="submit" value =">Go To 3B">
</form>
<%}
}else{ %>
<form action="professorRegister.jsp" method="post">
    <input type="hidden" name="cmd" value="a">
    <input type="submit" value =">Go To 4B">
</form>
<%}%>

<form action="professorRegister.jsp" method="post">
    <input type="hidden" name="cmd" value="ng">
    <select name="matricola">
        <% for(User u: register.getUsers()){ %>
        <option value ="<%=u.getMatricola()%>">
            <%=u.getLastname()%>
        </option>
     <% }%>
    </select>
    Materia
    <select name ="materia">
        <% for(String s: p.getMateria()){ %>
        <option value ="<%=s%>">
            <%=s%>
        </option>
        <% }%>
    </select>    Voto
    <input type="text" name="voto">

    <select name = "tipo">
        <option value = "orale">orale</option>
        <option value = "scritto">Scritto</option>
        <option value ="laboratorio">Laboratorio</option>
        </select>
    <input type="submit" value="Save">
 </form>

<form action="professorRegister.jsp" method="post">
<select name ="materia">
    <% for(String s: p.getMateria()){ %>
    <option value ="<%=s%>">
        <%=s%>
    </option>
    <% }%>
    <input type="hidden" name="cmd" value="mat">
    <input type='submit' name='submit'/>
</select>
</form>


<div id="pop1">
    Helllo
</div>



<br><br>
mettere delle frecce al posto  dei  bottoni e dargli una posizione
<% if(request.getAttribute("month") != null){
    MonthFactory mf = new MonthFactory();
    month m = (month)request.getAttribute("month");
    month pr;
    month sx;
    if(m.getIndex() == 1) {
         pr = mf.createMonth(12);
    } else {
         pr = mf.createMonth(m.getIndex()-1);
    }
    if(m.getIndex() == 12){
         sx = mf.createMonth(1);
    } else {
         sx = mf.createMonth(m.getIndex()+1);
    }
    String materia;
    if(request.getAttribute("materia")!= null)
        materia = request.getAttribute("materia").toString();
    else
        materia = p.getMateria().get(0);

%>
<form action="professorRegister.jsp" method="post">
    <input type="hidden" name="cmd" value="m">
    <input type="hidden" name="monthIndex" value="<%=pr.getIndex()%>">
    <input type="submit" value ="<%=pr.getName()%>">
</form>

    <%=m.getName()%>

<form action="professorRegister.jsp" method="post">
    <input type="hidden" name="cmd" value="m">
    <input type="hidden" name="monthIndex" value="<%=sx.getIndex()%>">
    <input type="submit" value ="<%=sx.getName()%>">
</form>



    <div class="tg-wrap"><table id="tg-yquL3" class="tg">
    <tr>
    <th class="tg-c3ow" colspan="6">
        <%=materia%>
    </th>
        <th class="tg-c3ow" colspan="6">
            <%=m.getName()%>
        </th>
    </tr>
        <tr>
            <td>
                Students:
            </td>

            <%for(int i = 1; i < m.getDay()+1; i++){%>
            <td><%= i %></td>
            <%}%>
            <td>media</td>
</tr>

<%

        for(User u: register.getUsers()){
%>          <br>
        <tr>
            <td>

<%=u.getLastname()%> <%=u.getName()%>
        </td>
            <%for(int i = 1; i < m.getDay()+1; i++){%>

                  <%
                  String result = "";
                  if(u.getGrades()!= null){
                  for (Grades g :u.getGrades()) {
                      Calendar cal = Calendar.getInstance();
                        cal.setTime(g.getData());
                            int day = cal.get(Calendar.DAY_OF_MONTH);
                            if(day == i){
                                    result = String.valueOf(g.getVoto());
                            }
                       }
                  }
                  if(u.getAssenze()!=null){
                      for(Assenze a: u.getAssenze()){
                      Calendar cal = Calendar.getInstance();
                        cal.setTime(a.getData());
                            int day = cal.get(Calendar.DAY_OF_MONTH);
                            if(day == i){
                                    result = "A";
                            }
                       }
                    }

                  %>
            <td><%=result%> </td>
            <%

              }
              %>
            <td><%=register.getMedia(u.getMatricola(),materia)%></td>
                <%

}

%>

    </div>
<%


}
%>
<%}%>
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


<script>

    var table = document.getElementById("tg-yquL3");
    if (table != null) {
        for (var i = 0; i < table.rows.length; i++) {
            for (var j = 0; j < table.rows[i].cells.length; j++)
                table.rows[i].cells[j].onclick = function () {
                    tableText(this);
                };
        }
    }

    function tableText(tableCell) {
       var y = document.getElementById("pop1");
        y.classList.add("succ1");
        setTimeout(function(){y.classList.remove("succ1"); }, 5000);
    console.log(y);
    }


</script>
</body>
</html>
