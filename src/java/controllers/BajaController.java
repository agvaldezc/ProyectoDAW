/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instancias.Maestro;
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
@WebServlet(name = "BajaController", urlPatterns = {"/BajaController"})
public class BajaController extends HttpServlet {

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
        
        //Se obtienen la ionformacion del profesor loggeado
        Maestro maestro = (Maestro) request.getSession().getAttribute("maestro");

        //Se obtiene el tipo de baja que se quiere realizar
        String url = "/menu.jsp";
        String baja = request.getParameter("baja");

        //Si la baja es de maestro
        if (baja.equals("maestro")) {
            try {
                //Registro que se quiere eliminar
                String nomina = request.getParameter("id");
                
                //Conexion con la base de datos
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                String query = "DELETE FROM Maestros WHERE nomina = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, nomina);
                pstmt.execute();
                connection.close();
            } catch (Exception e) {
                //Error SQL
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        
        //Baja de alumno
        if (baja.equals("alumno")) {
            try {
                //REgistro a eliminar
                String matricula = request.getParameter("id");
                
                //Conexion con la base de datos
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                String query = "DELETE FROM Alumnos WHERE matricula = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, matricula);
                pstmt.execute();
                connection.close();
            } catch (Exception e) {
                //Error SQL
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        
        //Baja de materia
        if (baja.equals("materia")) {
            try {
                //registro a eliminar
                String clave = request.getParameter("id");
                
                //Conexion con la base de datos
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                String query = "DELETE FROM Materias WHERE clave = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, clave);
                pstmt.execute();
                connection.close();
            } catch (Exception e) {
                //Error SQL
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        
        //Baja de salon
        if (baja.equals("salon")) {
            try {
                //Registro a eliminar
                String id = request.getParameter("id");
                
                //Conexion con la base de datos
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                String query = "DELETE FROM Salones WHERE id = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, id);
                pstmt.execute();
                connection.close();
            } catch (Exception e) {
                //Error SQL
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        
        //Baja de curso
        if (baja.equals("curso")) {
            try {
                //Registro a eliminar
                int id = Integer.parseInt(request.getParameter("id"));
                
                //Conexion con la base de datos
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                String maestroCurso = "delete from MaestroCurso where idCurso = ? and nomina = ?";
                PreparedStatement statement = connection.prepareStatement(maestroCurso);
                
                statement.setInt(1, id);
                statement.setString(2, maestro.getNomina());
                
                statement.executeUpdate();
                
                //Se resta 1 a cursos impartidos por este profesor
                PreparedStatement restarCursosImpartidos = connection.prepareStatement("update Maestros set cursosImpartidos = cursosImpartidos - 1 where nomina = ?");
                
                restarCursosImpartidos.setString(1, maestro.getNomina());
                
                restarCursosImpartidos.executeUpdate();
                
                String query = "DELETE FROM Cursos WHERE id = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, id);
                pstmt.execute();
                connection.close();
            } catch (Exception e) {
                //Error SQL
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        
        
        //ServletContext context = request.getServletContext();
        //RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        //dispatcher.forward(request, response);

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
