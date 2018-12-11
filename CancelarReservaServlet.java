/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import beans.Persona;
import beans.Reserva;
import beans.Tour;
import facades.PersonaFacade;
import facades.ReservaFacade;
import facades.TourFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
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
@WebServlet(name = "CancelarReservaServlet", urlPatterns = {"/CancelarReservaServlet"})
public class CancelarReservaServlet extends HttpServlet {

    @EJB
    private final TourFacade tourFacade = new TourFacade();
    @EJB
    private final PersonaFacade userFacade = new PersonaFacade();
    @EJB
    private final ReservaFacade reservaFacade = new ReservaFacade();
    
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
        
        HttpSession session = request.getSession();
        Persona actualUser = (Persona) session.getAttribute("actualUser");
        Tour actualTour = (Tour) session.getAttribute("actualTour");
        
        Reserva r = getReserva(actualUser,actualTour);
        
        List<Reserva> l = actualUser.getReservaList();
        l.remove(r);
        actualUser.setReservaList(l);
        
        l = actualTour.getReservaList();
        l.remove(r);
        actualTour.setReservaList(l);
        
        tourFacade.edit(actualTour);
        userFacade.edit(actualUser);
        reservaFacade.remove(r);
        
        session.setAttribute("actualUser", actualUser);
        session.setAttribute("actualTour", actualTour);
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/vistatour.jsp");
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

    private Reserva getReserva(Persona actualUser, Tour actualTour) {
        for(Reserva r : actualUser.getReservaList()){
            if(r.getIdTourTour().getIdTour().equals(actualTour.getIdTour())) return r;
        }
        return null;
    }

}
