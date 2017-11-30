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
import model.dao.DirectoryDAO;

/**
 *
 * @author Yuta
 */
@WebServlet(name = "Directory", urlPatterns = {"/Directory"})
public class Directory extends HttpServlet {
    
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
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser != null){
            if(loginUser.getUserTypeString().equals("Board Member")){
                if(request.getParameter("userid") == null || request.getParameter("userid").equals("")){
                    response.sendRedirect("OfficerMain?action=directory");
                }else{
                    String userId = request.getParameter("userid");
                    User selectedUser = DirectoryDAO.getUserById(userId);
                    request.setAttribute("selectedUser", selectedUser);
                    request.getRequestDispatcher("officer/accounts/directory/home.jsp").forward(request, response);
                }
            }
            else if(loginUser.getUserTypeString().equals("Homeowner")){
                if(request.getParameter("userid") == null || request.getParameter("userid").equals("")){
                    response.sendRedirect("HomeownerMain?action=directory");
                }else{
                    String userId = request.getParameter("userid");
                    User selectedUser = DirectoryDAO.getUserById(userId);
                    request.setAttribute("selectedUser", selectedUser);
                    request.getRequestDispatcher("homeowner/accounts/directory/home.jsp").forward(request, response);
                }
            }
            
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

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
