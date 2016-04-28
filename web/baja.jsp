<%-- 
    Document   : baja
    Created on : Apr 23, 2016, 5:50:54 PM
    Author     : Gonzalez
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="imports/libraries.html"></jsp:include>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h1>Bajas</h1>
            <div class="container">
                <table class="table table-bordered">
                <%
                    String baja = request.getParameter("baja");
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    Statement stmt = connection.createStatement();
                %>

                <%if (baja.equals("maestro")) {%>
                <thead>
                    <tr>
                        <th>Nomina</th>
                        <th>Nombre</th>
                        <th>Telefono</th>
                        <th>Mail</th>
                        <th>Cursos Impartidos</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Maestros";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("nomina")%></td>
                        <td><%= rs.getString("nombre")%></td>
                        <td><%= rs.getString("telefono")%></td>
                        <td><%= rs.getString("mail")%></td>
                        <td><%= rs.getString("cursosImpartidos")%></td>
                        <td><a href="BajaController?baja=maestro&nomina=<%=rs.getString("nomina")%>">Eliminar</a></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                <p>No hay datos!</p>
                <%
                    }
                %>

                <%if (baja.equals("materia")) {%>
                <thead>
                    <tr>
                        <th>Clave</th>
                        <th>Nombre</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Materias";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("clave")%></td>
                        <td><%= rs.getString("nombre")%></td>
                        <td><a href="BajaController?baja=materia&clave=<%=rs.getString("clave")%>">Eliminar</a></td>
                    </tr>
                    <%
                        }
                    }
                    %>
                    
                    <%if (baja.equals("alumno")) {%>
                <thead>
                    <tr>
                        <th>Matricula</th>
                        <th>Nombre</th>
                        <th>Telefono</th>
                        <th>Mail</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Alumnos";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("matricula")%></td>
                        <td><%= rs.getString("nombre")%></td>
                        <td><%= rs.getString("telefono")%></td>
                        <td><%= rs.getString("mail")%></td>
                        <td><a href="BajaController?baja=alumno&matricula=<%=rs.getString("matricula")%>">Eliminar</a></td>
                    </tr>
                    <%
                        }
                    }
                    %>
                    
                    <%if (baja.equals("curso")) {%>
                <thead>
                    <tr>
                        <th>Clave Materia</th>
                        <th>Grupo</th>
                        <th>Horario</th>
                        <th>Salon</th>
                        <th>Ingles</th>
                        <th>Honors</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Cursos";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("claveMateria")%></td>
                        <td><%= rs.getString("numeroGrupo")%></td>
                        <td><%= rs.getString("horario")%></td>
                        <td><%= rs.getString("salon")%></td>
                        <td><%= rs.getInt("ingles")%></td>
                        <td><%= rs.getInt("honors")%></td>
                        <td><a href="BajaController?baja=curso&id=<%=rs.getString("id")%>">Eliminar</a></td>
                    </tr>
                    <%
                        }
                    }
                    %>
                


                <%
                    stmt.close();
                    connection.close();
                %>
                </tbody>
            </table>
        </div>
    </body>
</html>
