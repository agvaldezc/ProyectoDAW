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
            <script>
                function borrar(obj, id, baja)
                {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (xhttp.readyState == 4 && xhttp.status == 200) {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
                                var ren = obj.parentNode.rowIndex;
                                document.getElementById("tabla-bajas").deleteRow(ren);
                            }
                        }
                    };
                    xhttp.open("GET", "BajaController?baja=" + baja + "&id=" + id, true);
                    xhttp.send();
                }
            </script>
            <h1>Bajas</h1>
            <div class="container">
                <table class="table table-bordered" id="tabla-bajas">
                <%
                    String baja = request.getParameter("baja");
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    Statement stmt = connection.createStatement();
                %>

                <%if (baja.equals("maestro")) {%>
                <h3>Maestros</h3>
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
                        <td><button onclick="borrar(this.parentNode, '<%=rs.getString("nomina")%>', 'maestro')">Eliminar</button></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                <p>!</p>
                <%
                    }
                %>

                <%if (baja.equals("materia")) {%>
                <h3>Materias</h3>
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
                        <td><button onclick="borrar(this.parentNode, '<%=rs.getString("clave")%>', 'materia')">Eliminar</button></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    
                    <%if (baja.equals("salon")) {%>
                <h3>Salones</h3>
                <thead>
                    <tr>
                        <th>Numero</th>
                        <th>Capacidad</th>
                        <th>Administracion</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String query = "SELECT * FROM Salones";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("id")%></td>
                        <td><%= rs.getString("capacidad")%></td>
                        <td><%= rs.getString("administracion")%></td>
                        <td><button onclick="borrar(this.parentNode, '<%=rs.getString("id")%>', 'salon')">Eliminar</button></td>
                    </tr>
                    <%
                            }
                        }
                    %>

                    <%if (baja.equals("alumno")) {%>
                    <h3>Alumnos</h3>
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
                        <td><button onclick="borrar(this.parentNode, '<%=rs.getString("matricula")%>', 'alumno')">Eliminar</button></td>
                    </tr>
                    <%
                            }
                        }
                    %>

                    <%if (baja.equals("curso")) {%>
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
                        <td><%= rs.getString("claveMateria")%></td>
                        <td><%= rs.getString("numeroGrupo")%></td>
                        <td><%= rs.getString("horario")%></td>
                        <td><%= rs.getString("salon")%></td>
                        <td><%= rs.getInt("ingles")%></td>
                        <td><%= rs.getInt("honors")%></td>
                        <td><button onclick="borrar(this.parentNode, <%=rs.getString("id")%>, 'curso')">Eliminar</button></td>
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
