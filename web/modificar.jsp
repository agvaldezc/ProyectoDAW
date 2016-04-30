<%@page import="instancias.Alumno"%>
<%@page import="instancias.Maestro"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="imports/libraries.html"></jsp:include>
        <script src="imports/modificacion.js"></script>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
        <jsp:include page="imports/header.jsp"></jsp:include>
            <div class="container">
            <h1>Modificaciones</h1>
            <div class="container">
                <h3 style="color: red">Para modificar la información indicada con (*), favor de dar doble click al campo.</h3>
                <table class="table table-stripped" id="tabla-bajas">
                <%
                    String modificacion = request.getParameter("modificar");
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    Statement stmt = connection.createStatement();
                %>

                <%if (modificacion.equals("maestro")) {%>
                <h3>Maestros</h3>
                <thead>
                    <tr>
                        <th>Nomina</th>
                        <th>Nombre *</th>
                        <th>Telefono *</th>
                        <th>Mail *</th>
                        <th>Cursos Impartidos</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String query = "select * from Maestros";

                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td id="nomina" class="<%= rs.getString("nomina") %>"><%= rs.getString("nomina")%></td>
                        <td id="nombre" class="<%= rs.getString("nomina") %>" ondblclick='modificar(this, "maestro")'><%= rs.getString("nombre")%></td>
                        <td id="telefono" class="<%= rs.getString("nomina") %>" ondblclick='modificar(this, "maestro")'><%= rs.getString("telefono")%></td>
                        <td id="mail" class="<%= rs.getString("nomina") %>" ondblclick='modificar(this, "maestro")'><%= rs.getString("mail")%></td>
                        <td id="cursosImpartidos" class="<%= rs.getString("nomina") %>"><%= rs.getString("cursosImpartidos")%></td>
                    </tr>
                    <%
                        }
                    } 
                    %>

                <%if (modificacion.equals("materia")) {%>
                <h3>Materias</h3>
                <thead>
                    <tr>
                        <th>Clave *</th>
                        <th>Nombre *</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Materias";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td id="clave" class="<%= rs.getString("clave") %>" ondblclick='modificar(this, "materia")'><%= rs.getString("clave")%></td>
                        <td id="nombre" class="<%= rs.getString("clave") %>" ondblclick='modificar(this, "materia")'><%= rs.getString("nombre")%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <%if (modificacion.equals("salon")) {%>
                <h3>Salones</h3>
                <thead>
                    <tr>
                        <th>Numero *</th>
                        <th>Capacidad *</th>
                        <th>Administracion *</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Salones";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td id="id" class="<%= rs.getString("id") %>" ondblclick='modificar(this, "salon")'><%= rs.getString("id")%></td>
                        <td id="capacidad" class="<%= rs.getString("id") %>" ondblclick='modificar(this, "salon")'><%= rs.getString("capacidad")%></td>
                        <td id="administracion" class="<%= rs.getString("id") %>" ondblclick='modificar(this, "salon")'><%= rs.getString("administracion")%></td>
                    </tr>
                    <%
                            }
                        }
                    %>

                    <%if (modificacion.equals("alumno")) {%>
                    <h3>Alumnos</h3>
                <thead>
                    <tr>
                        <th>Matricula</th>
                        <th>Nombre *</th>
                        <th>Telefono *</th>
                        <th>Mail *</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Alumnos";
                        
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td id="matricula" class="<%= rs.getString("matricula") %>"><%= rs.getString("matricula")%></td>
                        <td id="nombre" class="<%= rs.getString("matricula") %>" ondblclick='modificar(this, "alumno")'><%= rs.getString("nombre")%></td>
                        <td id="telefono" class="<%= rs.getString("matricula") %>" ondblclick='modificar(this, "alumno")'><%= rs.getString("telefono")%></td>
                        <td id="mail" class="<%= rs.getString("matricula") %>" ondblclick='modificar(this, "alumno")'><%= rs.getString("mail")%></td>
                    </tr>
                    <%
                            }
                        }
                    %>

<!--                    <%if (modificacion.equals("curso")) {%>
                    <h3>Cursos</h3>
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
                        <td id="claveMateria" ondblclick='modificar(this, "curso")'><%= rs.getString("claveMateria")%></td>
                        <td id="numeroGrupo" ondblclick='modificar(this, "curso")'><%= rs.getString("numeroGrupo")%></td>
                        <td id="horario" ondblclick='modificar(this, "curso")'><%= rs.getString("horario")%></td>
                        <td i="salon" ondblclick='modificar(this, "curso")'><%= rs.getString("salon")%></td>
                        <td id="ingles" ondblclick='modificar(this, "curso")'><%= rs.getInt("ingles")%></td>
                        <td id="honors" ondblclick='modificar(this, "curso")'><%= rs.getInt("honors")%></td>
                    </tr>
                    <%
                            }
                        }
                    %>



                    <%
                        stmt.close();
                        connection.close();
                    %>
                </tbody>-->
            </table>
        </div>
    </body>
</html>
