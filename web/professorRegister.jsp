<%@ page import="controller.ProfessorRegister" %>
<%@ page import="model.Professor" %>
<%@ page import="model.User" %>
<%@ page import="model.Grades" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="factory.month" %>
<%@ page import="factory.MonthFactory" %>
<%@ page import="model.Assenze" %>
<%@ page import="java.util.GregorianCalendar" %>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div id="pop1">
    Hello
</div>


<ul>
    <li><a href="homeProfessor.jsp">Home</a></li>
    <li><a href="professorRegister.jsp">Register</a></li>
    <li><a >
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout" >
        </form></a></li>
</ul>
<div class="container-fluid col-sm-12" style="padding:30px">

<div align="center" class="col-sm-12"><h3>Welcome in the Register Page</h3></div><br><br>


<%
    if(session.getAttribute("professor") == null){
        response.sendRedirect("jsp.html");
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
<input type="submit" value ="Go To 3B">
</form>
<%}
}else{ %>
<form action="professorRegister.jsp" method="post">
    <input type="hidden" name="cmd" value="a">
    <input type="submit" value ="Go To 4B">
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



<br>
mettere delle frecce al posto  dei  bottoni e dargli una posizione
<% if(request.getAttribute("month") != null){
    MonthFactory mf = new MonthFactory();
    month m = (month)request.getAttribute("month");
    month pr;
    month sx;
    if(m.getIndex() == 1) {
         pr = mf.createMonth(12,m.getYear()-1);
    } else {
         pr = mf.createMonth(m.getIndex()-1,m.getYear());
    }
    if(m.getIndex() == 12){
         sx = mf.createMonth(1,m.getYear()+1);
    } else {
         sx = mf.createMonth(m.getIndex()+1,m.getYear());
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
    <input type="hidden" name="monthYear" value="<%=pr.getYear()%>">
    <input type="submit" value ="<%=pr.getName()%>">
</form>

    <%=m.getName()%>

<form action="professorRegister.jsp" method="post">
    <input type="hidden" name="cmd" value="m">
    <input type="hidden" name="monthIndex" value="<%=sx.getIndex()%>">
    <input type="hidden" name="monthYear" value="<%=sx.getYear()%>">
    <input type="submit" value ="<%=sx.getName()%>">
</form>

        <table style="margin:0px; width:100%" class="tableRegister">
    <tr>
    <th colspan="6">
        <%=materia%>
    </th>
        <th colspan="6">
            <%=m.getName()%> <%= m.getYear()%>
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</div>
</div>
</body>
</html>
