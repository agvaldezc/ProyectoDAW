<%-- 
    Document   : alta
    Created on : Apr 22, 2016, 10:18:32 AM
    Author     : Gonzalez
--%>
<%
    String error = (String) request.getAttribute("error");

    if (error == null) {
        error = "";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="imports/libraries.html"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function validar(val, idInput) {
                if (val.length < 1) {
                    document.getElementById(idInput).parentElement.className += " has-error";
                    return;
                } else {
                    document.getElementById(idInput).parentElement.className = "form-group has-success";
                }
            }
        </script>
    </head>
        <body>
        <jsp:include page="imports/header.jsp"></jsp:include>
        <%
            String alta = request.getParameter("alta");
        %>
        <%if (alta.equals("salon")) {%>
        <div class="col-md-6">
            <%
                if (error != "") {
                    out.println("<div class='alert alert-danger'>");
                    out.println("<strong>" + error + "</strong>");
                    out.println("</div>");
                }
            %>
            <h1>Alta Salon</h1>
            <form method="post" action="AltaController?alta=salon" role="form">
                <div class="form-group">
                    <label for="numero">Numero </label>
                    <input type="text" id="salonNum" name="numero" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="capacidad">Capacidad: </label>
                    <input type="text" id="salonCap" name="capacidad" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="administracion">Administracion </label>
                    <input type="text" id="salonAdmin" name="administracion" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
        <%}%>

        <%if (alta.equals("maestro")) {%>
        <div class="col-md-6">
            <%
                if (error != "") {
                    out.println("<div class='alert alert-danger'>");
                    out.println("<strong>" + error + "</strong>");
                    out.println("</div>");
                }
            %>
            <h1>Alta Maestro</h1>
            <form method="post" action="AltaController?alta=maestro" role="form">
                <div class="form-group">
                    <label for="nomina">Nomina </label>
                    <input type="text" id="maestroNom" name="nomina" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña </label>
                    <input type="text" id="maestroPass" name="password" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre </label>
                    <input type="text" id="maestroNomb" name="nombre" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="telefono">Telefono </label>
                    <input type="text" id="maestroTel" name="telefono" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="mail">Mail </label>
                    <input type="text" id="maestroMail" name="mail" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="cursos">Cursos impartidos </label>
                    <input type="number" id="maestroCur" name="cursos" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
        <%}%>
        
        <%if (alta.equals("materia")) {%>
        <div class="col-md-6">
            <%
                if (error != "") {
                    out.println("<div class='alert alert-danger'>");
                    out.println("<strong>" + error + "</strong>");
                    out.println("</div>");
                }
            %>
            <h1>Alta Materia</h1>
            <form method="post" action="AltaController?alta=materia" role="form">
                <div class="form-group">
                    <label for="clave">Clave: </label>
                    <input type="text" id="salonNum" name="clave" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre: </label>
                    <input type="text" id="salonCap" name="nombre" class="form-control" onkeyup="validar(this.value, this.id)" required>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
        <%}%>
    </body>
</html>
