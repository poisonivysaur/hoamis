/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
 * A servlet for processing user sign up.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for user accounts.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */
@WebServlet(name = "SignupProcess", urlPatterns = {"/SignupProcess"})
public class SignupProcess extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int usertype = Integer.parseInt(request.getParameter("usertype"));
        
        if (!(Validate.checkUser(username, password))){
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            AddAccount.pazucc(username, password, usertype);
	    request.setAttribute("gago","Register ZUCCess");
            rs.forward(request, response);
        }
        else{
            RequestDispatcher rs = request.getRequestDispatcher("register.jsp");
            rs.include(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "A servlet for processing user sign up.";
    }// </editor-fold>

}
