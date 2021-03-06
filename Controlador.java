package Servlet;

import Datos.ConexionJDBC;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ConexionJDBC con = new ConexionJDBC();
        con.conectar();
        String sql = "UPDATE usuario SET nombre='" + request.getParameter("nombres") 
                + "', correo='" + request.getParameter("correos") + ", telefono='" + request.getParameter("telefonos") 
                + " where correo='" + request.getParameter("comprobar");
        
        try(PreparedStatement pst = con.getConexion().prepareStatement(sql) ){
            pst.setString(1, request.getParameter("comprobar"));
            pst.setString(2, request.getParameter("correos"));
            pst.setString(2, request.getParameter("nombres"));
            pst.setString(2, request.getParameter("telefonos"));
            pst.execute();
            
            request.getRequestDispatcher("salida.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
        
        ConexionJDBC con = new ConexionJDBC();
        con.conectar();
        String sql = "UPDATE usuario SET nombre='" + request.getParameter("nombres") 
                + "', correo='" + request.getParameter("correos") + ", telefono='" + request.getParameter("telefonos") 
                + " where correo='" + request.getParameter("comprobar");
        
        try(PreparedStatement pst = con.getConexion().prepareStatement(sql) ){
            pst.setString(1, request.getParameter("comprobar"));
            pst.setString(2, request.getParameter("correos"));
            pst.setString(2, request.getParameter("nombres"));
            pst.setString(2, request.getParameter("telefonos"));
            pst.execute();
            
            request.getRequestDispatcher("salida.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
