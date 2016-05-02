/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import instancias.Curso;
import instancias.Maestro;
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
        
        //Tipo de reporte que se quiere obtener
        String reporte = request.getParameter("reporte");
        
        //Salones disponibles a una hora especifica
        if (reporte.equals("salones")) {
            
            //Se crea un ArrayList para contener todos los resultados
            ArrayList<Salon> salones = new ArrayList<Salon>();
            
            //Sobre que horario se va a trabajar
            int horarioId = Integer.parseInt(request.getParameter("horario"));

            //Conexion a la base de datos
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            
            //Preparacion de query por seguridad
            String query = "select * from Salones where Salones.id not in (select s.id from Salones s, Horarios h, Cursos c where s.id = c.salon and c.horarioID = ?);";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, horarioId);
            
            //Ejecucion de query
            ResultSet result = statement.executeQuery();
            
            //Se procesan los resultados y se cuardan en el ArrayList
            while (result.next()) {
                Salon salon = new Salon(result.getString("id"), result.getInt("capacidad"), result.getString("administracion"));
               
                salones.add(salon);
            }
            
            //Se agrega la ArrayList como atributo dentro de la request
            request.setAttribute("salones", salones);
            
            String url = "/reportes.jsp";
            
            //Se redirige a la pagina para mostrar los resultados
            ServletContext context = request.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
            //Grupos registrados y su informacion
        } else if (reporte.equals("grupos")) {
            
            //Para guardar los resultados
            ArrayList<Curso> cursos = new ArrayList<Curso>();
            
            //MAteria sobre la cual se va a trabajar
            String claveMateria = request.getParameter("materia");
            
            //Conexion a la base de datos
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            
            //Preparacion de query por seguridad
            String query = "select * from Cursos join MaestroCurso on Cursos.id = MaestroCurso.idCurso "
                    + "join Maestros on Maestros.nomina = MaestroCurso.nomina join Horarios on Cursos.horarioID = Horarios.id where "
                    + "Cursos.claveMateria = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, claveMateria);
            
            //Ejecucion de query
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                
                //Procesamiento de resultado
                Curso curso = new Curso();
                
                curso.setMateria(claveMateria);
                curso.setGrupo(result.getInt("numeroGrupo"));
                curso.setProfesor(result.getString("nombre"));
                curso.setHorario(result.getString("horario"));
                curso.setSalon(result.getString("salon"));
                curso.setIngles(result.getBoolean("ingles"));
                curso.setHonors(result.getBoolean("honors"));
 
                cursos.add(curso);
            }
            
            //Se agrega el resultado a la request
            request.setAttribute("cursos", cursos);
            
            String url = "/reportes.jsp";
            
            //REdireccion
            ServletContext context = request.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
            //MAestros con clase a hora especifica
        } else if (reporte.equals("maestrosClase")) {
            
            ArrayList<Maestro> maestros = new ArrayList<Maestro>();
            
            int horarioId = Integer.parseInt(request.getParameter("horario"));
            System.out.println(horarioId);
            
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            
            String query = "select * from Maestros where Maestros.nomina in (select m.nomina from Maestros m, Cursos c, MaestroCurso mc where m.nomina = mc.nomina and mc.idCurso = c.id and c.horarioID = ?)";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, horarioId);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                Maestro salon = new Maestro(result.getString("nomina"), result.getString("password"), 
                        result.getString("nombre"), result.getString("telefono"), 
                        result.getString("mail"), result.getInt("cursosImpartidos"));
               
                maestros.add(salon);
            }
            
            request.setAttribute("maestrosClase", maestros);
            
            String url = "/reportes.jsp";
            
            ServletContext context = request.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
            //Maestros libres a hora especifica
        } else if (reporte.equals("maestrosLibre")) {
            
            ArrayList<Maestro> maestros = new ArrayList<Maestro>();
            
            int horarioId = Integer.parseInt(request.getParameter("horario"));
            System.out.println(horarioId);
            
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            
            String query = "select * from Maestros where Maestros.nomina not in (select m.nomina from Maestros m, Cursos c, MaestroCurso mc where m.nomina = mc.nomina and mc.idCurso = c.id and c.horarioID = ?)";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, horarioId);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                Maestro salon = new Maestro(result.getString("nomina"), result.getString("password"), 
                        result.getString("nombre"), result.getString("telefono"), 
                        result.getString("mail"), result.getInt("cursosImpartidos"));
               
                maestros.add(salon);
            }
            
            request.setAttribute("maestrosLibre", maestros);
            
            String url = "/reportes.jsp";
            
            ServletContext context = request.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
            //Que cursos imparte un profesor
        } else if(reporte.equals("cursos")){
            try {
            String nom = request.getParameter("nomina");
            String connectionURL = "jdbc:mysql://localhost:3306/ProyectoDAW";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            String queryString = "SELECT idCurso FROM MaestroCurso WHERE nomina = '"+nom+"'";
            PreparedStatement pstmt = connection.prepareStatement(queryString);
            ResultSet rs = pstmt.executeQuery(queryString);
            if(!rs.next()){
                out.println("El Maestro no tiene cursos");
            }else
                rs.beforeFirst();
            while (rs.next()) {
                queryString = "SELECT Horarios.horario, Cursos.claveMateria, Cursos.numeroGrupo, Cursos.salon, Cursos.ingles, Cursos.honors "
                        + "FROM Cursos "
                        + "INNER JOIN Horarios ON Cursos.horarioID=Horarios.id "
                        + "WHERE Cursos.id = '"+rs.getString("idCurso")+"'";
                PreparedStatement pstmt2 = connection.prepareStatement(queryString);
                ResultSet rs2 = pstmt2.executeQuery(queryString);
                if(!rs2.next()){
                out.println("El Maestro no tiene cursos");
            }else
                rs2.beforeFirst();
                while (rs2.next()) {
                out.println("<tr>");
                out.println("<td>"+rs2.getString("Cursos.claveMateria")+"</td>");
                out.println("<td>"+rs2.getInt("Cursos.numeroGrupo")+"</td>");
                out.println("<td>" + rs2.getString("Horarios.horario") + "</td>");
                out.println("<td>" + rs2.getString("Cursos.salon") + "</td>");
                if(rs2.getBoolean("Cursos.ingles")){
                    out.println("<td>Si</td>");
                }else
                    out.println("<td>No</td>");
                if(rs2.getBoolean("Cursos.honors"))
                    out.println("<td>Honors</td>");
                else
                    out.println("<td>No</td>");
                out.println("</tr>");
                }
            }

            connection.close();

        } catch (Exception e) {
            //Error SQL
            System.out.println(e);
        }
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
