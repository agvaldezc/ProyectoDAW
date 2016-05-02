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
        
        //Se crea una sesión nueva al intentar iniciar sesión.
        HttpSession session = request.getSession();
        
        //Inicializa la variable error y URL
        String error = "";
        String url = "/index.jsp";
        
        //Se obtiene el rol de la cuenta que se quiere ingresar y las
        //  credenciales introducidas por el usuario
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (role != null) {
        
            if (role.equals("maestro") && username != null && password != null) {
                
                
                //Conexion con la base de datos
                String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                Connection connection = DriverManager.getConnection(connectionURL, "root", "root");

                //Query de validacion de credenciales
                String queryString = "Select * from Maestros where nomina = ? and password = ?";

                //Preparamos el query por cuestiones de seguridad---------
                PreparedStatement pstmt = connection.prepareStatement(queryString);

                pstmt.setString(1, username);
                pstmt.setString(2, password);
                //---------------------------------------------------------

                //Se ejecuta el query y se obtiene un ResultSet si es que 
                //  se ha tenido exito en el inicio de sesion
                ResultSet result = pstmt.executeQuery();

                if (result.next()) {

                    //Se crea el objeto con la información del profesor
                    // dentro de la sesión para poder ser utilizada en otros
                    //      casos.
                    Maestro maestro = new Maestro(result.getString("nomina"),
                            result.getString("password"), result.getString("nombre"), result.getString("telefono"),
                            result.getString("mail"), result.getInt("cursosImpartidos"));
                    
                    //Se insertan los parametros dentro de la request
                    session.setAttribute("maestro", maestro);
                    request.setAttribute("error", error);
                    session.setAttribute("role", role);
                    
                    //Se manda al menu principal si el proceso tuvo exito
                    url = "/menu.jsp";

                } else {
                    //Se crea el mensaje de error y se guarda en al request
                    error = "Usuario y/o contraseña inválido";
                    request.setAttribute("error", error);
                }
            } 
        } else {
            
            //Si ya existia sesion se redirige al menu
            url = "/menu.jsp";
        }
        
        //Se redirige hacia la pagina correspondiente
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
