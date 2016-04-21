<%-- 
    Document   : index
    Created on : Apr 19, 2016, 5:45:26 PM
    Author     : agvaldezc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="imports/libraries.html"></jsp:include>
        <title>Index</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="imports/header.html"></jsp:include>
            <div class="col-md-6">
                <h1>Acceder al sistema como alumno</h1>
                <form method="post" action="AuthenticationController?role=alumno" role="form">
                    <div class="form-group">
                        <label for="username">Usuario: </label>
                        <input type="text" name="username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña: </label>
                        <input type="text" name="password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
            <div class="col-md-6">
                <h1>Acceder al sistema como administrador</h1>
                <form method="post" action="AuthenticationController?role=admin" role="form">
                    <div class="form-group">
                        <label for="username">Usuario: </label>
                        <input type="text" name="username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña: </label>
                        <input type="text" name="password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </body>
</html>
