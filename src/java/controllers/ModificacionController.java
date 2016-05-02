/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author agvaldezc
 */
public class ModificacionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String tipo = request.getParameter("tipo");
        String cambio = request.getParameter("cambio");
        String columna = request.getParameter("columna");
        String registro = request.getParameter("registro");
        
        String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
        Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
        
        if (tipo.equals("maestro")) {
            
            String query = "update Maestros set " + columna + " = ? where nomina = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, cambio);
            statement.setString(2, registro);
            
            statement.executeUpdate();
            
        } else if (tipo.equals("alumno")) {
            
            String query = "update Alumnos set " + columna + " = ? where matricula = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, cambio);
            statement.setString(2, registro);
            
            statement.executeUpdate();
            
        } else if (tipo.equals("curso")) {
            
            String query = "update Cursos set " + columna + " = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, cambio);
            statement.setString(2, registro);
            
            statement.executeUpdate();
            
        } else if (tipo.equals("salon")) {
            
            String query = "update Salones set " + columna + " = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, cambio);
            statement.setString(2, registro);
            
            statement.executeUpdate();
            
        } else if (tipo.equals("materia")) {
            
            String query = "update Materias set " + columna + " = ? where clave = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, cambio);
            statement.setString(2, registro);
            
            statement.executeUpdate(); 
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ModificacionController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModificacionController.class.getName()).log(Level.SEVERE, null, ex);
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