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

/**
 *
 * @author Gonzalez
 */
@WebServlet(name = "ReporteCursosController", urlPatterns = {"/ReporteCursosController"})
public class ReporteCursosController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String nom = request.getParameter("nomina");
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            String queryString = "SELECT idCurso FROM MaestroCurso WHERE nomina = '" + nom + "'";
            PreparedStatement pstmt = connection.prepareStatement(queryString);
            ResultSet rs = pstmt.executeQuery(queryString);
            if (!rs.next()) {
                out.println("El maestro no tiene cursos");
            } else {
                rs.beforeFirst();
            }
            while (rs.next()) {
                queryString = "SELECT Horarios.horario, Cursos.claveMateria, Cursos.numeroGrupo, Cursos.salon, Cursos.ingles, Cursos.honors "
                        + "FROM Cursos "
                        + "INNER JOIN Horarios ON Cursos.horarioID=Horarios.id "
                        + "WHERE Cursos.id = '" + rs.getString("idCurso") + "'";
                PreparedStatement pstmt2 = connection.prepareStatement(queryString);
                ResultSet rs2 = pstmt2.executeQuery(queryString);
                if (!rs2.next()) {
                    out.println("El maestro no tiene cursos");
                } else {
                    rs2.beforeFirst();
                }
                while (rs2.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs2.getString("Cursos.claveMateria") + "</td>");
                    out.println("<td>" + rs2.getInt("Cursos.numeroGrupo") + "</td>");
                    out.println("<td>" + rs2.getString("Horarios.horario") + "</td>");
                    out.println("<td>" + rs2.getString("Cursos.salon") + "</td>");
                    if (rs2.getBoolean("Cursos.ingles")) {
                        out.println("<td>Si</td>");
                    } else {
                        out.println("<td>No</td>");
                    }
                    if (rs2.getBoolean("Cursos.honors")) {
                        out.println("<td>Honors</td>");
                    } else {
                        out.println("<td>No</td>");
                    }
                    out.println("</tr>");
                }
            }

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
