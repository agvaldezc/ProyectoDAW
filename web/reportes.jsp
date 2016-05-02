<%-- 
    Document   : reportes
    Created on : May 1, 2016, 9:07:18 PM
    Author     : agvaldezc
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                if (reporte.equals("cursos")) {%>
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
            <%}%>
        </div>
    </body>
</html>
