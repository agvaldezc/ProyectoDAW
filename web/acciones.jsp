<%-- 
    Document   : acciones
    Created on : May 1, 2016, 11:56:07 PM
    Author     : Gonzalez
--%>

<%@page import="java.sql.*"%>
<%
    String error = (String) request.getAttribute("error");
    String accion = request.getParameter("accion");
    if (error == null) {
        error = "";
    }
    if (accion == null) {
        accion = "";
    }
    System.out.println("accion: " + accion);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="imports/libraries.html"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>

        <%
            if (error != "") {
                out.println("<div class='alert alert-danger'>");
                out.println("<strong>" + error + "</strong>");
                out.println("</div>");
            }
        %>
        <%if (accion.equals("maestro")) {%>
        <h1>Cambiar maestro</h1>
        <div class="col-md-12">
            <form method="post" action="AccionController" role="form">
                <div class="form-group">
                    <label for="maestro">Seleccionar Maestro </label>
                    <select name="maestro" class="form-control" required onChange="desplegar(this.options[this.selectedIndex].value)">
                        <option disabled selected>Selecciona un maestro</option>
                        <%
                            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                            Statement stmt = connection.createStatement();

                            String query = "SELECT * FROM Maestros";
                            ResultSet rs = stmt.executeQuery(query);
                            while (rs.next()) {
                        %>
                        <option value="<%= rs.getString("nomina")%>"> <%= rs.getString("nombre")%> - <%= rs.getString("nomina")%> </option>
                        <%
                            }
                        %>
                    </select>
                    <label for="curso">Seleccionar Curso </label>
                    <select name="curso" class="form-control" required onChange="desplegar(this.options[this.selectedIndex].value)">
                        <option disabled selected>Selecciona un curso</option>
                        <%
                            query = "SELECT * FROM Cursos";
                            rs = stmt.executeQuery(query);
                            while (rs.next()) {
                        %>
                        <option value="<%= rs.getString("id")%>">Clave de la Materia: <%= rs.getString("claveMateria")%> Numero de grupo: <%= rs.getString("numeroGrupo")%> </option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <input type="submit" value="Cambiar" class="form-control"/>
            </form>
        </div>
        <%}%>

    </body>
</html>
