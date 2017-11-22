/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import DAO.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leebet-PC
 */
@WebServlet(name = "LoginFB", urlPatterns = {"/LoginFB"})
public class LoginFB extends HttpServlet {




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
        String username = request.getParameter("ue");
        HttpSession session=request.getSession();  
        User user = new User();
        if(Validate.checkUser(username, "1")){
            
            
            user.setUser_id(user.getUserID(request.getParameter("ue")));
            user.setUsername(request.getParameter("ue"));
            
            session.setAttribute("uname",request.getParameter("un"));
            session.setAttribute("users", user.getUsers());
            session.setAttribute("groups", Group.getAllGroups(user.getUser_id()));
            
            session.setAttribute("UserObj",user);
            response.sendRedirect("Home.jsp");
        }
        else{
            user.fbLog(username);
           
            user.setUser_id(user.getUserID(request.getParameter("username")));
            user.setUsername(request.getParameter("username"));
            
            session.setAttribute("uname",request.getParameter("username"));
            session.setAttribute("users", user.getUsers());
            session.setAttribute("groups", Group.getAllGroups(user.getUser_id()));
            
            session.setAttribute("UserObj",user);
            response.sendRedirect("Home.jsp");
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
