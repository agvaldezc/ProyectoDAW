/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instancias.Salon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author agvaldezc
 */
public class ReporteController extends HttpServlet {

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
        
        
        String reporte = request.getParameter("reporte");
        
        if (reporte.equals("salones")) {
            ArrayList<Salon> salones = new ArrayList<Salon>();
            
            int horarioId = Integer.parseInt(request.getParameter("horario"));
            System.out.println(horarioId);
            
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            
            String query = "select * from Salones where Salones.id not in (select s.id from Salones s, Horarios h, Cursos c where s.id = c.salon and c.horarioID = ?);";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, horarioId);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                Salon salon = new Salon(result.getString("id"), result.getInt("capacidad"), result.getString("administracion"));
                
                System.out.println(result.getString("id"));
                System.out.println(result.getString("capacidad"));
                System.out.println(result.getString("administracion"));
                
                salones.add(salon);
            }
            
            request.setAttribute("salones", salones);
            
            String url = "/reportes.jsp";
            
            ServletContext context = request.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
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
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
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
