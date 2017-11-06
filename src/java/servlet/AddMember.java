
package servlet;

import DAO.Group;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * A servlet for adding invited members by other members.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for adding accounts.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */
@WebServlet(name = "AddMember", urlPatterns = {"/AddMember"})
public class AddMember extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        
        
        String a = request.getParameter("groupname");
        String[] members = request.getParameterValues("members");
        Group g = new Group(a);
        // instantiating a group from the DB
        
        
	request.setAttribute("groupDet",g);
        boolean d = g.AddMembers(g.getGroup_id(), members);
        RequestDispatcher rs = request.getRequestDispatcher("GroupDet");
        rs.include(request, response);
        rs.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "A servlet for adding invited members by other members.";
    }// </editor-fold>

}
