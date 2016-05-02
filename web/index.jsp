<%-- 
    Document   : index
    Created on : Apr 19, 2016, 5:45:26 PM
    Author     : agvaldezc
--%>


<%@page import="instancias.Alumno"%>
<%@page import="instancias.Maestro"%>
<% 
    String error = (String) request.getAttribute("error");
    Maestro maestro = (Maestro) request.getSession().getAttribute("maestro");
    Alumno alumno = (Alumno) request.getSession().getAttribute("alumno");
    
    if (error == null) {
        error = "";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="imports/libraries.html"></jsp:include>
        <title>Index</title>
    </head>
    <body>
        <jsp:include page="imports/header.jsp"></jsp:include>
        <div class="container">
            <%
                if (error != "") {
                    out.println("<div class='alert alert-danger'>");
                        out.println("<strong>" + error + "</strong>");
                    out.println("</div>");
                }
            %>
            <div class="col-md-12">
                <h1>Acceder al sistema como administrador</h1>
                <form method="post" action="AuthenticationController?role=maestro" role="form">
                    <div class="form-group">
                        <label for="username">Usuario: </label>
                        <input type="text" name="username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña: </label>
                        <input type="password" name="password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </body>
</html>
