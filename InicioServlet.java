package Servlets;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Guillermo
 */
@WebServlet(name = "InicioServlet", urlPatterns = {"/"})
public class InicioServlet extends HttpServlet {

    private String url = "jdbc:postgresql://localhost:5432/postgres",user="postgres",password="root";
    
    //private Connection getConnection(){
    //    Connection con = null;
     //   try {
     //       con = DriverManager.getConnection(url,user,password);
     //       System.out.println("Conexion realizada con extio");
     //   }catch (SQLException e){
     //       System.out.println("SE HA PRODUCIDO UN ERROR!!");
     //       System.out.println(e.getMessage());
     ////   }
     //   return con;
   // }
    
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
        
        HttpSession session = request.getSession();
        session.setAttribute("usuario", false);
        session.setAttribute("email", false);
        session.setAttribute("tlf", true);
        session.setAttribute("idioma", false);
        session.setAttribute("rol", false);
        
        //Connection con = getConnection();
        //System.out.println(con.getSchema());
        //PreparedStatement statement = con.prepareStatement("INSERT INTO tables.persona VALUES (?,?,?,?,?,?,?,?,?);");
        //statement.setInt(1,0);
        //statement.setString(2, "prueba");
        //statement.setString(3, "prueba");
        //statement.setString(4, "prueba");
        //statement.setString(5, "prueba");
        //statement.setString(6, "algo@algo.es");   
        //statement.setString(7, "6516");
        //statement.setString(8, "prueba");
        //statement.setString(9, "POR ACTIVAR");
        //statement.execute();
        //int rowsUpdated = statement.executeUpdate(); // execute the update
        //System.out.println("Rows affected: " + rowsUpdated);
        //con.close();
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/registro.jsp");
        rd.forward(request, response);
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
            Logger.getLogger(InicioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InicioServlet.class.getName()).log(Level.SEVERE, null, ex);
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