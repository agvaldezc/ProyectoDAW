<%-- 
    Document   : menu
    Created on : Apr 21, 2016, 9:33:28 PM
    Author     : agvaldezc
--%>

<%@page import="instancias.Alumno"%>
<%@page import="instancias.Maestro"%>
<%
    String role = (String) session.getAttribute("role");
    boolean esMaestro = false;
    boolean esAlumno = false;
    String nombre = "";
    
    if (role.equals("maestro")) {
        
        Maestro maestro = (Maestro) session.getAttribute("maestro");
        esMaestro = true;
        nombre = maestro.getNombre();
        
    } else if (role.equals("alumno")) {
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        esAlumno = true;
        nombre = alumno.getNombre();
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../imports/libraries.html"></jsp:include>
        <title>Menu</title>
    </head>
    <body>
        <jsp:include page="../imports/header.jsp"></jsp:include>
        <div class="container">
            <h1>Bienvenido/a <%= nombre %></h1>
            
            <%  if (esMaestro) {
                    out.print("<div class='list-group'>" +
                            "<h3>Altas</h3>" +
                            "<a href='alta.jsp?alta=alumno' class='list-group-item'>Dar de alta un alumno en el sistema</a>" +
                            "<a href='alta.jsp?alta=maestro' class='list-group-item'>Dar de alta un maestro en el sistema</a>" +
                            "<a href='alta.jsp?alta=materia' class='list-group-item'>Dar de alta una materia en el sistema</a>" +
                            "<a href='alta.jsp?alta=salon' class='list-group-item'>Dar de alta un salon en el sistema</a>" +
                            "<a href='alta.jsp?alta=curso' class='list-group-item'>Dar de alta un curso en el sistema</a>" +
                        "</div>" +
                         "<div class='list-group'>" +
                            "<h3>Bajas</h3>" +
                            "<a href='baja.jsp?baja=alumno' class='list-group-item'>Dar de baja un alumno en el sistema</a>" +
                            "<a href='baja.jsp?baja=maestro' class='list-group-item'>Dar de baja un maestro en el sistema</a>" +
                            "<a href='baja.jsp?baja=materia' class='list-group-item'>Dar de baja un materia en el sistema</a>" +
                            "<a href='baja.jsp?baja=salon' class='list-group-item'>Dar de baja un salon en el sistema</a>" +
                            "<a href='baja.jsp?baja=curso' class='list-group-item'>Dar de baja un curso en el sistema</a>" +
                        "</div>" +
                        "<div class='list-group'>" +
                            "<h3>Modificaciones</h3>" +
                            "<a href='editar.jsp?editar=alumno' class='list-group-item'>Editar un alumno en el sistema</a>" +
                            "<a href='editar.jsp?editar=maestro' class='list-group-item'>Editar un maestro en el sistema</a>" +
                            "<a href='editar.jsp?editar=materia' class='list-group-item'>Editar una materia en el sistema</a>" +
                            "<a href='editar.jsp?editar=salon' class='list-group-item'>Editar un salon en el sistema</a>" +
                            "<a href='editar.jsp?editar=curso' class='list-group-item'>Editar un curso en el sistema</a>" +
                        "</div>");
                }
            
                if (esAlumno) {
                    out.print("<div class='list-group'>" +
                                "<h3>Altas</h3>" +
                                "<a href='registraCurso.jsp?alta=alumno' class='list-group-item'>Dar de alta un alumno en el sistema</a>" +
                            "</div>" +
                            "<div class='list-group'>" +
                                "<h3>Modificaciones</h3>" +
                                "<a href='eliminaCurso.jsp?alta=maestro' class='list-group-item'>Dar de alta un maestro en el sistema</a>" +
                            "</div>");
                }
            %>
        </div>
    </body>
</html>
