/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import beans.Idioma;
import beans.Persona;
import facades.IdiomaFacade;
import facades.PersonaFacade;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@WebServlet(name = "RegistrarUsuarioServlet", urlPatterns = {"/RegistrarUsuarioServlet"})
public class RegistrarUsuarioServlet extends HttpServlet {

    @EJB
    private PersonaFacade personaFacade = new PersonaFacade();
    @EJB
    private IdiomaFacade lanFacade = new IdiomaFacade();
    
    public  String getCadena (int longitud){
        String cadenaAleatoria="";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
            char c = (char)r.nextInt(255);
            //System.out.println("char:"+c);
            if ( (c >= '0' && c <=9) || (c >='A' && c <='Z') ){
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }
    
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
        String msg = "Bienvenido a TrotamundoTours señor/a "+(String) session.getAttribute("nombre")+".\n\n Solo queda un paso mas para "
                + "que este 100% registrado en nuestra web. "
                + "Introduzca este codigo en la pagina web para"
                + " oficializar su registro: "+session.getAttribute("code")+"\n\n Un saludo!";
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
     * @throws javax.mail.internet.AddressException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AddressException, MessagingException {
        System.out.println("EEEEEMPIEZA EL SERVLEEEET");
        HttpSession session = request.getSession();
        
        try {
            
            System.out.println("HE ENTRADO");
            
            String nombre = (String) request.getParameter("nombre");
            System.out.println(nombre);
            
            String apellido1 = (String) request.getParameter("ap1");
            System.out.println(apellido1);
            
            String apellido2 = (String )request.getParameter("ap2");
            System.out.println(apellido2);
            
            String usuario = (String) request.getParameter("usuario");
            System.out.println(usuario);
            
            String password = (String) request.getParameter("password");
            System.out.println(password);
            
            String email = (String) request.getParameter("email");
            System.out.println(email);
            
            String tel = (String) request.getParameter("telefono");
            System.out.println(tel);
            
            String[] idiomas = request.getParameterValues("idioma");
            List<String> langs = new ArrayList<>();
            langs.addAll(Arrays.asList(idiomas));
            System.out.println(langs);
            
            String rol = request.getParameter("rol");
            System.out.println(rol);
            
            String code = getCadena(6);
            String host="";
            
            session.setAttribute("code", code);
            session.setAttribute("nombre", nombre);
            
            int telefono = Integer.parseInt(tel);
            Scanner sc = new Scanner(email);
            sc.useDelimiter("@");
            host = "smtp." + sc.next();
            session.setAttribute("host", host);
            
            if(idiomas.length==0) { // No se han seleccionado idiomas
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/registro.jsp");
                rd.forward(request, response);
            }
            if(rol.equals("")){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/registro.jsp");
                rd.forward(request, response);
            }
            
            enviarEmail(session,request);
        
            String newID = newPersonaID();
            Persona user = new Persona(newID);
            user.setNombre(nombre);
            user.setApellido1(apellido1);
            user.setApellido2(apellido2);
            user.setContraseña(password);
            user.setEmail(email);
            user.setUsuario(usuario);
            user.setTelefono(BigInteger.valueOf(telefono));
            user.setRol(rol);
            user.setEstado("POR ACTIVAR");
            List<Idioma> list = new ArrayList<>();
            for(String s : idiomas){
                String newIdiomaID = newIdiomaID();
                Idioma i = new Idioma(newIdiomaID);
                i.setLanguage(s);
                i.setIdPersona(user);
                list.add(i);
                
                lanFacade.create(i);
            }
            user.setIdiomaList(list);
            
            personaFacade.create(user);
            
            session.setAttribute("user", user);
        
        }
        catch(AddressException e){ // Errores del email
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/registro.jsp");
            rd.forward(request, response);
        }
        catch(MessagingException | NoSuchElementException e){ // Errores del email
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/registro.jsp");
            rd.forward(request, response);
        }catch (NumberFormatException e) { // Se han introducido letras o decimales
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/registro.jsp");
            rd.forward(request, response);
        }
        // No hay @algo.com
                    
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
            Logger.getLogger(RegistrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("ESTOY EN EL DOPOST");
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(RegistrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private String newPersonaID() {
        int i = personaFacade.findAll().size()+1;
        return String.valueOf(i);
    }
    
    private String newIdiomaID(){
        int i = lanFacade.findAll().size()+1;
        return String.valueOf(i);
    }

}
