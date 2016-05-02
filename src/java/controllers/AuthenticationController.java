/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instancias.Alumno;
import instancias.Maestro;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author agvaldezc
 */
public class AuthenticationController extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        String error = "";
        String url = "/index.jsp";
        
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (role != null) {
        
            if (role.equals("maestro") && username != null && password != null) {

                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");

                String queryString = "Select * from Maestros where nomina = ? and password = ?";

                PreparedStatement pstmt = connection.prepareStatement(queryString);

                pstmt.setString(1, username);
                pstmt.setString(2, password);

                ResultSet result = pstmt.executeQuery();

                if (result.next()) {

                    Maestro maestro = new Maestro(result.getString("nomina"),
                            result.getString("password"), result.getString("nombre"), result.getString("telefono"),
                            result.getString("mail"), result.getInt("cursosImpartidos"));

                    session.setAttribute("maestro", maestro);
                    request.setAttribute("error", error);
                    session.setAttribute("role", role);

                    url = "/menu.jsp";

                } else {
                    error = "Usuario y/o contraseña inválido";
                    request.setAttribute("error", error);
                }

            } else if (role.equals("alumno") && username != null && password != null) {

                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");

                String queryString = "Select * from Alumnos where matricula = ? and password = ?";

                PreparedStatement pstmt = connection.prepareStatement(queryString);

                pstmt.setString(1, username);
                pstmt.setString(2, password);

                ResultSet result = pstmt.executeQuery();

                if (result.next()) {

                    Alumno alumno = new Alumno(result.getString("matricula"),
                            result.getString("password"), result.getString("nombre"), result.getString("telefono"),
                            result.getString("mail"));

                    session.setAttribute("alumno", alumno);
                    request.setAttribute("error", error);
                    session.setAttribute("role", role);

                    url = "/menu.jsp";

                } else {
                    error = "Usuario y/o contraseña inválido";
                    request.setAttribute("error", error);
                }
            }
        } else {
            url = "/menu.jsp";
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
            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
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
