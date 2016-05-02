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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author Gonzalez
 */
@WebServlet(name = "AccionController", urlPatterns = {"/AccionController"})
public class AccionController extends HttpServlet {

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
        String url = "/reportes.jsp?reporte=cursos";
        String error = "";
        
        try {
            String nomina = request.getParameter("maestro");
            String cursoId = request.getParameter("curso");
            
            System.out.println(nomina);
            System.out.println(cursoId);
            
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            Statement stmt = connection.createStatement();
            double[] hinicio = new double[20];
            double[] hfinal = new double[20];
            int cont = 0;
            String query = "SELECT Horarios.hora_inicio, Horarios.hora_fin " + "FROM Cursos "
                    + "INNER JOIN Horarios ON Cursos.horarioID=Horarios.id "
                    + "WHERE Cursos.id = '" + cursoId + "'";
            PreparedStatement pstmt0 = connection.prepareStatement(query);
            ResultSet rs0 = pstmt0.executeQuery(query);
            System.out.println("1");
            while (rs0.next()) {
                hinicio[cont] = rs0.getDouble("Horarios.hora_inicio");
                hfinal[cont] = rs0.getDouble("Horarios.hora_fin");
                cont++;
                System.out.println("horarios");
            }

            String queryString = "SELECT idCurso FROM MaestroCurso WHERE nomina = '" + nomina + "'";
            PreparedStatement pstmt = connection.prepareStatement(queryString);
            ResultSet rs = pstmt.executeQuery(queryString);
            if (!rs.next()) {
                queryString = "UPDATE MaestroCurso set nomina = ? WHERE idCurso = ?";
                pstmt = connection.prepareStatement(queryString);
                pstmt.setString(1, nomina);
                pstmt.setString(2, cursoId);
                pstmt.executeUpdate();
                System.out.println("Se cambio exitosamente");
                ServletContext context = request.getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } else {
                rs.beforeFirst();
            }
            while (rs.next()) {
                queryString = "SELECT Horarios.hora_inicio, Horarios.hora_fin, Cursos.claveMateria, Cursos.numeroGrupo, Cursos.salon, Cursos.ingles, Cursos.honors "
                        + "FROM Cursos "
                        + "INNER JOIN Horarios ON Cursos.horarioID=Horarios.id "
                        + "WHERE Cursos.id = '" + rs.getString("idCurso") + "'";
                PreparedStatement pstmt2 = connection.prepareStatement(queryString);
                ResultSet rs2 = pstmt2.executeQuery(queryString);
                while (rs2.next()) {
                    for (int i = 0; i < hinicio.length; i++) {
                        if (rs2.getDouble("Horarios.hora_inicio") != hinicio[i] && rs2.getDouble("Horarios.hora_fin") != hfinal[i]) {
                            queryString = "UPDATE MaestroCurso set nomina = ? WHERE idCurso = ?";
                            pstmt = connection.prepareStatement(queryString);
                            pstmt.setString(1, nomina);
                            pstmt.setString(2, cursoId);
                            pstmt.executeUpdate();
                            ServletContext context = request.getServletContext();
                            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                            dispatcher.forward(request, response);
                        }else{
                            error = "Datos incorrectos";
                            request.setAttribute("error", error);
                            url = "/acciones.jsp";
                        }
                           
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
            url = "/acciones.jsp";
            
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
