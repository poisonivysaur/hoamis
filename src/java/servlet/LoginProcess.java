package others;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.dao.User;
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

import model.dao.User;
import dao.Policy;
import static others.Validate.*;

/**
 *
 * @author Leebet-PC
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
        String userID = request.getParameter("username");
        String pWord = request.getParameter("password");

         /**  && Validate.checkUserType(userID, pWord) == 1 **/ 
        if (Validate.checkUser(userID, pWord) && Validate.checkUserType(userID, pWord) == 2){
            HttpSession session=request.getSession();  
            User user = new User();
            
            //user.setUserID(request.getParameter("username"));
            
            session.setAttribute("userID",request.getParameter("username"));
            session.setAttribute("allPolicies", Policy.sql_getAllPolicies());       
            
            //send to admin home
            session.setAttribute("UserObj", user);
            System.out.println("Login Successful!");
            response.sendRedirect("userhome.jsp");            
        }
        else{
            System.out.println(request.getParameter("username"));
            System.out.println(request.getParameter("password"));
	    request.setAttribute("username","bad");
            RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
            rs.include(request, response);
        } 
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
