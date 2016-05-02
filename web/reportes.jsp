<%-- 
    Document   : reportes
    Created on : May 1, 2016, 9:07:18 PM
    Author     : agvaldezc
--%>
<%@page import="instancias.Maestro"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="instancias.Salon"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Salon> salones = (ArrayList) request.getAttribute("salones");
    ArrayList<Maestro> maestrosLibre = (ArrayList) request.getAttribute("maestrosLibre");
    ArrayList<Maestro> maestrosClase = (ArrayList) request.getAttribute("maestrosClase");
    

    if (salones == null) {
        salones = new ArrayList<Salon>();
    }
    
    if (maestrosLibre == null) {
        maestrosLibre = new ArrayList<Maestro>();
    }
    
    if (maestrosClase == null) {
        maestrosClase = new ArrayList<Maestro>();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="imports/libraries.html"></jsp:include>
            <title>Menu</title>
        </head>
        <body>
        <jsp:include page="imports/header.jsp"></jsp:include>
            <script>
                function desplegar(nom)
                {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (xhttp.readyState == 4 && xhttp.status == 200) {
                            document.getElementById("tabla-cursos-body").innerHTML = xhttp.responseText;
                        }
                    };
                    xhttp.open("GET", "ReporteCursosController?nomina=" + nom, true);
                    xhttp.send();
                }
            </script>
            <div class="container">
            <%
                String reporte = request.getParameter("reporte");
                if (reporte.equals("cursos")) { %>
            <h1>Cursos</h1>
            <div class="col-md-12">
                <form method="post" role="form">
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
                    </div>
                </form>
            </div>
            <div class="container">
                <table class="table table-stripped" id="tabla-cursos">
                    <thead>
                        <tr>
                            <th>Clave Materia</th>
                            <th>Grupo</th>
                            <th>Horario</th>
                            <th>Salon</th>
                            <th>Ingles</th>
                            <th>honors</th>
                        </tr>
                    </thead>
                    <tbody id="tabla-cursos-body">

                    </tbody>
                </table>
            </div>
            <% } %>

            <%
                if (reporte.equals("salones")) {%>
            <div class="col-md-12">
                <form method="post" action="ReporteController?reporte=salones" role="form">
                    <div class="form-group">
                        <label for="nomina">Horario: </label>
                        <select name="horario" class="form-control" required>
                            <%
                                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");

                                PreparedStatement statement = connection.prepareStatement("select * from Horarios");

                                ResultSet result = statement.executeQuery();

                                while (result.next()) {

                            %>
                            <option value="<%= result.getInt("id")%>"><%= result.getString("horario")%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Buscar" class="form-control"/>
                    </div>
            </div>
            <div class="col-md-12">
                <table class="table table-stripped" id="tabla-bajas">
                    <thead>
                        <tr>
                            <th>Numero</th>
                            <th>Capacidad</th>
                            <th>Administracion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Salon salon : salones) {
                        %>
                        <tr>
                            <td id="id"><%= salon.getId()%></td>
                            <td id="capacidad"><%= salon.getCapacidad()%></td>
                            <td id="administracion"><%= salon.getAdministracion()%></td>
                        </tr>
                        <%
                                }
                           
                        %>
                    </tbody>
                </table>
            </div>
            <% } %>
            
            <% if (reporte.equals("maestrosClase"))  {%>
            <div class="col-md-12">
                <form method="post" action="ReporteController?reporte=maestrosClase" role="form">
                <div class="form-group">
                    <label for="nomina">Horario: </label>
                    <select name="horario" class="form-control" required>
            <%
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                
                PreparedStatement statement = connection.prepareStatement("select * from Horarios");
                
                ResultSet result = statement.executeQuery();
                
                while (result.next()) {
          
            %>
                    <option value="<%= result.getInt("id") %>"><%= result.getString("horario")%></option>
            <%
                }
            %>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" value="Buscar" class="form-control"/>
                </div>
            </div>
            <div class="col-md-12">
                <table class="table table-stripped" id="tabla-bajas">
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
                            for (Maestro maestro : maestrosClase) {
                        %>
                        <tr>
                            <td id="id"><%= maestro.getNomina() %></td>
                            <td id="capacidad"><%= maestro.getNombre() %></td>
                            <td id="administracion"><%= maestro.getTelefono() %></td>
                            <td id="administracion"><%= maestro.getEmail() %></td>
                            <td id="administracion"><%= maestro.getCursosImpartidos() %></td>
                        </tr>
                        <%
                                }
                           
                        %>
                    </tbody>
                </table>
            </div>
                
            <% } %>
            
            <% if (reporte.equals("maestrosLibre"))  {%>
            <div class="col-md-12">
                <form method="post" action="ReporteController?reporte=maestrosLibre" role="form">
                <div class="form-group">
                    <label for="nomina">Horario: </label>
                    <select name="horario" class="form-control" required>
            <%
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                
                PreparedStatement statement = connection.prepareStatement("select * from Horarios");
                
                ResultSet result = statement.executeQuery();
                
                while (result.next()) {
          
            %>
                    <option value="<%= result.getInt("id") %>"><%= result.getString("horario")%></option>
            <%
                }
            %>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" value="Buscar" class="form-control"/>
                </div>
            </div>
            <div class="col-md-12">
                <table class="table table-stripped" id="tabla-bajas">
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
                            for (Maestro maestro : maestrosLibre) {
                        %>
                        <tr>
                            <td id="id"><%= maestro.getNomina() %></td>
                            <td id="capacidad"><%= maestro.getNombre() %></td>
                            <td id="administracion"><%= maestro.getTelefono() %></td>
                            <td id="administracion"><%= maestro.getEmail() %></td>
                            <td id="administracion"><%= maestro.getCursosImpartidos() %></td>
                        </tr>
                        <%
                                }
                           
                        %>
                    </tbody>
                </table>
            </div>
                
            <% } %>
        </div>
    </body>
</html>
