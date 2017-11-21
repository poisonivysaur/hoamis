
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.sql.*;
import model.Usergroup;

import model.dao.User;

/**
 *
 * A servlet for validating user information.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for user accounts.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */
@WebServlet(name = "LoginProcess", urlPatterns = {"/LoginProcess"})
public class LoginProcess extends HttpServlet {

  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (Validate.checkUser(username, password)){
            HttpSession session=request.getSession();  
            User user = new User(request.getParameter("username"));
            
            session.setAttribute("uname",request.getParameter("username"));
            session.setAttribute("users", user.getUsers());
            session.setAttribute("groups", Usergroup.getAllGroups(user.getUserID()));
            
            session.setAttribute("UserObj",user);
            //response.sendRedirect("officer/com-activity/groupsMgt/Home.jsp");
            if (request.getParameter("module").equals( "groups-management"))
                response.sendRedirect("Home");
            else if(request.getParameter("module").equals( "document-management"))
                response.sendRedirect("HomeDocu");
                
                
        }
        else{
            
	    request.setAttribute("gago","Login unZUCCED");
            response.sendRedirect("index.jsp");
        }
    }
    @Override
    public String getServletInfo() {
        return "A servlet for validating user information";
    }// </editor-fold>

}
