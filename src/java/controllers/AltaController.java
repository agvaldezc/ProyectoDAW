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

        //Se obtiene la informacion del maestro que utiliza el sistema
        Maestro maestro = (Maestro) request.getSession().getAttribute("maestro");

        //Inicializa mensaje de error y URL
        String error = "";
        String url = "/menu.jsp";

        //Se obtiene el tipo de alta que se realizara
        String alta = request.getParameter("alta");

        //Si el alta es de un salon
        if (alta.equals("salon")) {
            
            //Inicializar las variables en valores default
            int capacidad = 0;
            String administracion = "";
            String id = "";
            
            //Se obtienen los valores mandados por la forma para registrarse
            id = request.getParameter("numero");
            capacidad = Integer.parseInt(request.getParameter("capacidad"));
            administracion = request.getParameter("administracion");
            
            
            //Si los datos fueron llenados de manera éxitosa...
            if (id != "" && capacidad != 0 && administracion != "") {

                try {
                    //Conexion con la based de datos
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    
                    //Prepara query por seguridad
                    String queryString = "INSERT INTO Salones (id, capacidad, administracion) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, id);
                    pstmt.setInt(2, capacidad);
                    pstmt.setString(3, administracion);

                    //Se ejecuta el query de registro
                    pstmt.execute();
                    connection.close();

                } catch (Exception e) {
                    //Error al realizar registro
                    error = "Exception";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                //Error en los datos proporcionados
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        //Si el alta es de un maestro
        if (alta.equals("maestro")) {
            
            //Inicializar variables default
            String nomina = "";
            String password = "";
            String nombre = "";
            String telefono = "";
            String mail = "";
            int cursos = 0;
            
            //Obtenemos parametros de la request
            nomina = request.getParameter("nomina");
            password = request.getParameter("password");
            nombre = request.getParameter("nombre");
            telefono = request.getParameter("telefono");
            mail = request.getParameter("mail");

            //Validamos que todo sea correcto
            if (nomina != "" && password != "" && nombre != "" && telefono != "" && mail != "") {

                try {
                    //Conexion a la base de datos
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    
                    //Se prepara el query por seguridad
                    String queryString = "INSERT INTO Maestros (nomina, password, nombre, telefono, mail, cursosImpartidos) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, nomina);
                    pstmt.setString(2, password);
                    pstmt.setString(3, nombre);
                    pstmt.setString(4, telefono);
                    pstmt.setString(5, mail);
                    pstmt.setInt(6, cursos);

                    //Se ejecuta el query
                    pstmt.execute();
                    
                    //Se cierra la conexion con la base de datos
                    connection.close();

                } catch (Exception e) {
                    //Error de SQL
                    error = "Exception";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                //Datos invalidos
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }
        
        //Si el alta es de una materia
        if (alta.equals("materia")) {
            
            //Variables default
            String clave = "";
            String nombre = "";
            clave = request.getParameter("clave");
            nombre = request.getParameter("nombre");

            //Verifica que todo este en orden
            if (clave != "" && nombre != "") {

                try {
                    //Conexion con la base de datos
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    
                    //Preparacion del query por seguridad
                    String queryString = "INSERT INTO Materias (clave, nombre) VALUES (?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, clave);
                    pstmt.setString(2, nombre);

                    //Ejecucion del query
                    pstmt.execute();
                    
                    //Cierra conexion con la base de datos
                    connection.close();

                } catch (Exception e) {
                    //Error SQL
                    error = "Exception";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                //Datos invalidos
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }
        
        //Si el alta es de un alumno
        if (alta.equals("alumno")) {
            
            //VAriables default
            String matricula = "";
            String password = "";
            String nombre = "";
            String telefono = "";
            String mail = "";
            
            //Se obtienen los datos introducidos por el usuario
            matricula = request.getParameter("matricula");
            password = request.getParameter("password");
            nombre = request.getParameter("nombre");
            telefono = request.getParameter("telefono");
            mail = request.getParameter("mail");

            //Verificacion de que todo es correcto
            if (matricula != "" && password != "" && nombre != "" && telefono != "" && mail != "") {

                try {
                    
                    //Conexion con la base de datos
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    
                    //Preparacion del query por seguridad
                    String queryString = "INSERT INTO Alumnos (matricula, password, nombre, telefono, mail) VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, matricula);
                    pstmt.setString(2, password);
                    pstmt.setString(3, nombre);
                    pstmt.setString(4, telefono);
                    pstmt.setString(5, mail);
                    
                    //Ejecucion del query
                    pstmt.execute();
                    
                    //Cierre de conexion con la base de datos
                    connection.close();
                    
                } catch (Exception e) {
                    //Error SQL
                    error = "Exception";
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                //Datos invalidos
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        //Si el alta es de un curso
        if (alta.equals("curso")) {
            
            //Variables default
            String materia = "";
            String grupo = "";
            int horario = 0;
            String salon = "";
            int ingles = 0;
            int honors = 0;
            
            //Se obtienen los datos introducidos por el usuario
            materia = request.getParameter("materia");
            grupo = request.getParameter("grupo");
            
    
            horario = Integer.parseInt(request.getParameter("horario"));
            
            salon = request.getParameter("salon");
            ingles = Integer.parseInt(request.getParameter("ingles"));
            honors = Integer.parseInt(request.getParameter("honors"));
            
            
            //VAlidamos que todo esta en orden
            if (materia != "" && grupo != "" && salon != "") {

                try {
                    
                    //Conexion con la base de datos
                    String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
                    Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
                    
                    //Preparacion del query por seguridad
                    String query = "select * from Cursos join MaestroCurso on Cursos.id = MaestroCurso.idCurso join Materias "
                            + "on Materias.clave = Cursos.claveMateria join Horarios on Horarios.id = Cursos.horarioID where "
                            + "nomina = ? and Cursos.horarioID = ?";
        
                    PreparedStatement statement = connection.prepareStatement(query);
        
                    statement.setString(1, maestro.getNomina());
                    statement.setInt(2, horario);
                    
                    //Se ejecuta el primer query para verificar si ocurre 
                    //  o no un empalme con los registros y la nueva entidad
                    ResultSet rs = statement.executeQuery();
                    
                    if (!rs.next()) {
                    
                    //Prepearcion de query por seguridad
                    String queryString = "INSERT INTO Cursos (claveMateria, numeroGrupo, horarioID, salon, ingles, honors) VALUES (?, ?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(queryString);

                    pstmt.setString(1, materia);
                    pstmt.setString(2, grupo);
                    pstmt.setInt(3, horario);
                    pstmt.setString(4, salon);
                    pstmt.setInt(5, ingles);
                    pstmt.setInt(6, honors);

                    //Introduce la informacion en la base de datos
                    pstmt.execute();
                    
                    //Se obtiene la informacion del registro recien ingresado
                    String retreiveQuery = "select id from cursos where claveMateria = ? and numeroGrupo = ? and horarioID = ? and salon = ? and ingles = ? and honors = ?";
                    
                    PreparedStatement retreiveInsert = connection.prepareStatement(retreiveQuery);
                    
                    retreiveInsert.setString(1, materia);
                    retreiveInsert.setString(2, grupo);
                    retreiveInsert.setInt(3, horario);
                    retreiveInsert.setString(4, salon);
                    retreiveInsert.setInt(5, ingles);
                    retreiveInsert.setInt(6, honors);
                    
                    //Se obtiene el id del nuevo curso registrado para asi
                    //  vincular al profesor con ese curso
                    ResultSet insert = retreiveInsert.executeQuery();
                    
                    insert.next();
                    
                    String maestroCursoInsert = "insert into MaestroCurso (nomina, idCurso, porcentaje) values (?, ?, 100)";
                    
                    PreparedStatement maestroCurso = connection.prepareStatement(maestroCursoInsert);
                    
                    maestroCurso.setString(1, maestro.getNomina());
                    maestroCurso.setInt(2, insert.getInt("id"));
                    
                    maestroCurso.executeUpdate();
                    
                    //Se actualiza la cantidad de cursos impartidos por el profesor
                    PreparedStatement aumentarCursosImpartidos = connection.prepareStatement("update Maestros set cursosImpartidos = cursosImpartidos + 1 where nomina = ?");
                    
                    aumentarCursosImpartidos.setString(1, maestro.getNomina());
                    
                    aumentarCursosImpartidos.executeUpdate();
                    
                    connection.close();
                    
                    } else {
                        //Mensaje de error por emplarme
                        error = "El curso que quiere registrar se empalma con " + rs.getString("clave") + 
                                rs.getString("nombre") + " en el horario " + rs.getString("horario") + ".\n Favor de cambiar el horario del curso"
                                + " que se quiere registrar.";
                        request.setAttribute("error", error);
                        url = "/alta.jsp";
                    }

                } catch (Exception e) {
                    //Error SQL
                    error = e.getMessage();
                    request.setAttribute("error", error);
                    url = "/alta.jsp";
                }

            } else {
                //Datos invalidos
                error = "Datos incorrectos";
                request.setAttribute("error", error);
                url = "/alta.jsp";
            }
        }

        //Se redrige a donde sea necesario
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
