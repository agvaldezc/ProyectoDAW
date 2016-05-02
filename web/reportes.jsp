<%-- 
    Document   : reportes
    Created on : May 1, 2016, 9:07:18 PM
    Author     : agvaldezc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="instancias.Salon"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Salon> salones = (ArrayList) request.getAttribute("salones");
    
    if (salones == null) {
        salones = new ArrayList<Salon>();
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
        <div class="container">
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
                            <td id="id"><%= salon.getId() %></td>
                            <td id="capacidad"><%= salon.getCapacidad() %></td>
                            <td id="administracion"><%= salon.getAdministracion() %></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
