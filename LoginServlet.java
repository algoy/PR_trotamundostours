import beans.Persona;
import facades.PersonaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro
 */
public class LoginServlet extends HttpServlet {

    @EJB
    private PersonaFacade userFacade = new PersonaFacade();
    
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
        
            String usuario = request.getParameter("usario"); // Guardo el usuario introducido
            String password = request.getParameter("password"); // Guardo la contraseña introducida
            
            //Busco en la BD si hay algun usuario con ese usuario y contraseña
            List<Persona> userList = userFacade.findByUserAndPassword(usuario,password);
            
            if(userList.isEmpty()){ // Si la lista esta vacia -> no se ha encontrado el usuario, hay error en el usuario o la password
                // Redirijo a errorlogin.jsp
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/errorlogin.jsp");
                rd.forward(request, response);
            }
            else { // Else -> Se ha encontrado el usuario
                Persona user = userList.get(0); // Me traigo el usuario
                if(user.getEstado().equals("POR ACTIVAR")){ // Si su estado es POR ACTIVAR
                    // Redirijo a validarRegistro.jsp
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/validarRegistro.jsp");
                    rd.forward(request, response);
                }
                else { // Else -> Usuario ya verificado
                    HttpSession session = request.getSession(); // Creo la variable HttpSession
                    session.setAttribute("actualUser", user); // Guardo en la variable session el usuario encontrado
                    //Redirijo a usuario.jsp
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/usuario.jsp");
                    rd.forward(request, response);        
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
