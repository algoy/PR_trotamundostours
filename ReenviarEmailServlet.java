/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@WebServlet(name = "ReenviarEmailServlet", urlPatterns = {"/ReenviarEmailServlet"})
public class ReenviarEmailServlet extends HttpServlet {

    public void enviarEmail(HttpSession session,HttpServletRequest request) throws AddressException, MessagingException{
        
        Properties props = new Properties(); // Lo guarda en un Hashtable -> Map
        props.setProperty("mail.smtp.host", (String) session.getAttribute("host"));
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", (String) session.getAttribute("email"));
        props.setProperty("mail.smtp.auth", "true");
        // Preparamos la sesion
        Session s = Session.getDefaultInstance(props);
        // Construimos el mensaje
        MimeMessage message = new MimeMessage(s); // Mime style message, se le pasa la session
        //la persona k tiene k verificar
        message.setFrom(new InternetAddress((String) session.getAttribute("email"))); //  Set the RFC 822 "From" header field.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) session.getAttribute("email"))); // Add the given addresses to the specified recipient type.
        message.addHeader("Disposition-Notification-To",(String) session.getAttribute("email")); //  Add this value to the existing values for this header_name.
        message.setSubject("Confirmacion de registro de TrotamundoTours"); // Set the "Subject" header field.
        String msg = "Bienvenido a TrotamundoTours señor/a "+(String) session.getAttribute("nombre")+".\n\n Solo queda un paso mas para que este 100% registrado en nuestra web. Introduzca este codigo en la pagina web para oficializar su registro: "+session.getAttribute("code")+"\n\n Un saludo!";
        message.setText(msg,"ISO-8859-1","html"); //  Convenience method that sets the given String as this part's content, with a primary MIME type of "text" and the specified MIME subtype.
        // Lo enviamos.
        // Transport: An abstract class that models a message transport.
        // SMTP: Protocolo para transferencia simple de correo
        Transport t = s.getTransport("smtp");
        t.connect("trotamundotours2018@gmail.com", "Trotamundos2018"); // Connect to the current host using the specified username and password.
        t.sendMessage(message, message.getAllRecipients()); //Send the Message to the specified list of addresses.
 
        // Cierre.
        t.close();
    }
    
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
            throws ServletException, IOException, MessagingException {
        
        HttpSession session = request.getSession();
        enviarEmail(session, request);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/validarRegistro.jsp");
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
        } catch (MessagingException ex) {
            Logger.getLogger(ReenviarEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (MessagingException ex) {
            Logger.getLogger(ReenviarEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
