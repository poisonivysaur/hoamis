package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  

/**
 *
 * A servlet for forwarding user information into home page.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for user accounts.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */
@WebServlet(urlPatterns = {"/HomeDocu"})
public class HomeDocu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //response.sendRedirect("officer/com-activity/groupsMgt/Home.jsp");
        request.getRequestDispatcher("officer/com-mgt/docuMgt/Home.jsp").forward(request, response);
        System.out.println("THIS IS HOME");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("officer/com-mgt/docuMgt/Home.jsp").forward(request, response);
        System.out.println("THIS IS HOME");
    }

    @Override
    public String getServletInfo() {
        return "A servlet for forwarding user information into home page.";
    }// </editor-fold>

}
