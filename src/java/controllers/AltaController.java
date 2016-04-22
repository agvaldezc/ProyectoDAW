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
        String url = "/menu.jsp";
        
        String alta = request.getParameter("alta");
        String capacidad = "";
        String administracion = "";
        String id = "";
        
        if (alta.equals("salon")) {
            id = request.getParameter("numero");
            capacidad = request.getParameter("capacidad");
            administracion = request.getParameter("administracion");
        }
        
        if (alta.equals("salon") && id != "" && capacidad != "" && administracion != "") {
            
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            
            String queryString = "INSERT INTO Salones (id, capacidad, administracion) VALUES (?, ?, ?)";
            
            PreparedStatement pstmt = connection.prepareStatement(queryString);
            
            pstmt.setString(1, id);
            pstmt.setString(2, capacidad);
            pstmt.setString(3, administracion);
            
            pstmt.execute();
            
            connection.close();
            
            } else {
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
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
