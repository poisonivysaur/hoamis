
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.*;
import javax.servlet.http.HttpSession;

/**
 *
 * A servlet for user joining a group.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for user groups.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */
@WebServlet(name = "JoinGroup", urlPatterns = {"/JoinGroup"})
public class JoinGroup extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            boolean a  = User.joinGroup(Integer.parseInt(request.getParameter("uid")), Integer.parseInt(request.getParameter("gid")));
             HttpSession session=request.getSession();  
        
            User user = new User();
            Group g = new Group();
            
            // reinitiating session
            user.setUser_id(user.getUserID(request.getParameter("uname")));
            user.setUsername(request.getParameter("uname"));
            session.setAttribute("groups", g.getAllGroups(user.getUser_id()));
            response.sendRedirect("Home.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "A servlet for user joining a group.";
    }// </editor-fold>

}
