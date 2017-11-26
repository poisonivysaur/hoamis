/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.dao.UserManagementDAO;

/**
 *
 * @author Yuta
 */
@WebServlet(name = "DeactivateAccount", urlPatterns = {"/DeactivateAccount"})
public class DeactivateAccount extends HttpServlet {
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
     *Redirects back to the OfficerMain?action=userManagement if logged in.
     *Redirects back to the Login if not logged in.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser != null){
            response.sendRedirect("SysAdminMain?action=userManagement");
        }else{
            response.sendRedirect("Login");
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
    /*
     *Deactivates the account on POST Method access.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userid = request.getParameter("userid");
        //System.out.println("User ID: " + userid);
        String msg = "";
        if(UserManagementDAO.deactivateUser(userid)){
            msg = "User account has been deactivated.";
        }else{
            msg = "Unable to deactivate this user account.";
        }
        
        request.getSession().setAttribute("msg2", msg);
        response.sendRedirect("SysadminMain?action=userManagement");
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
