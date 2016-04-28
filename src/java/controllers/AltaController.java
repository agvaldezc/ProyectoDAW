/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author Gonzalez
 */
@WebServlet(name = "AltaController", urlPatterns = {"/AltaController"})
public class AltaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //HttpSession session = request.getSession();

        String error = "";
        String url = "/WEB-INF/menu.jsp";

        String alta = request.getParameter("alta");

        if (alta.equals("salon")) {
            int capacidad = 0;
            String administracion = "";
            String id = "";
            id = request.getParameter("numero");
            capacidad = Integer.parseInt(request.getParameter("capacidad"));
            administracion = request.getParameter("administracion");

            if (id != "" && capacidad != 0 && administracion != "") {

                try {
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    String queryString = "INSERT INTO Salones (id, capacidad, administracion) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, id);
                    pstmt.setInt(2, capacidad);
                    pstmt.setString(3, administracion);

                    pstmt.execute();
                    connection.close();

                } catch (Exception e) {
                    error = "Datos incorrectos";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        if (alta.equals("maestro")) {
            String nomina = "";
            String password = "";
            String nombre = "";
            String telefono = "";
            String mail = "";
            int cursos = 0;
            nomina = request.getParameter("nomina");
            password = request.getParameter("password");
            nombre = request.getParameter("nombre");
            telefono = request.getParameter("telefono");
            mail = request.getParameter("mail");
            if (!request.getParameter("cursos").equals(null)) {
                cursos = Integer.parseInt(request.getParameter("cursos"));
            }

            if (nomina != "" && password != "" && nombre != "" && telefono != "" && mail != "") {

                try {

                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    String queryString = "INSERT INTO Maestros (nomina, password, nombre, telefono, mail, cursosImpartidos) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, nomina);
                    pstmt.setString(2, password);
                    pstmt.setString(3, nombre);
                    pstmt.setString(4, telefono);
                    pstmt.setString(5, mail);
                    pstmt.setInt(6, cursos);

                    pstmt.execute();
                    connection.close();

                } catch (Exception e) {
                    error = "Datos incorrectos";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        if (alta.equals("materia")) {
            String clave = "";
            String nombre = "";
            clave = request.getParameter("clave");
            nombre = request.getParameter("nombre");

            if (clave != "" && nombre != "") {

                try {
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    String queryString = "INSERT INTO Materias (clave, nombre) VALUES (?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, clave);
                    pstmt.setString(2, nombre);

                    pstmt.execute();
                    connection.close();

                } catch (Exception e) {
                    error = "Datos incorrectos";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        if (alta.equals("alumno")) {
            String matricula = "";
            String password = "";
            String nombre = "";
            String telefono = "";
            String mail = "";
            matricula = request.getParameter("matricula");
            password = request.getParameter("password");
            nombre = request.getParameter("nombre");
            telefono = request.getParameter("telefono");
            mail = request.getParameter("mail");

            if (matricula != "" && password != "" && nombre != "" && telefono != "" && mail != "") {

                try {
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");

                    String queryString = "INSERT INTO Alumnos (matricula, password, nombre, telefono, mail) VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, matricula);
                    pstmt.setString(2, password);
                    pstmt.setString(3, nombre);
                    pstmt.setString(4, telefono);
                    pstmt.setString(5, mail);

                    pstmt.execute();

                    connection.close();
                } catch (Exception e) {
                    error = "Datos incorrectos";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        if (alta.equals("curso")) {
            String materia = "";
            String grupo = "";
            int horario = 0;
            String salon = "";
            int ingles = 0;
            int honors = 0;
            materia = request.getParameter("materia");
            grupo = request.getParameter("grupo");
            horario = Integer.parseInt(request.getParameter("horario"));
            salon = request.getParameter("salon");
            ingles = Integer.parseInt(request.getParameter("ingles"));
            honors = Integer.parseInt(request.getParameter("honors"));

            if (materia != "" && grupo != "" && salon != "") {

                try {
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");

                    String queryString = "INSERT INTO Cursos (claveMateria, numeroGrupo, horario, salon, ingles, honors) VALUES (?, ?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, materia);
                    pstmt.setString(2, grupo);
                    pstmt.setInt(3, horario);
                    pstmt.setString(4, salon);
                    pstmt.setInt(5, ingles);
                    pstmt.setInt(6, honors);

                    pstmt.execute();
                    connection.close();

                } catch (Exception e) {
                    error = "Datos incorrectos";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AltaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AltaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
