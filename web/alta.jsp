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
    </head>
    <body>
        <jsp:include page="imports/header.jsp"></jsp:include>
        <%
            String alta = request.getParameter("alta");
        %>
        <%if(alta.equals("salon")){%>
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
                        <label for="capacidad">Numero </label>
                        <input type="text" name="numero" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="capacidad">Capacidad: </label>
                        <input type="text" name="capacidad" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="administracion">Administracion </label>
                        <input type="text" name="administracion" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        <%}%>
    </body>
</html>
