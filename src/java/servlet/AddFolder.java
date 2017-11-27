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
import model.*;
import model.dao.FolderDAO;
/**
 *
 * @author Leebet-PC
 */
@WebServlet(name = "AddFolder", urlPatterns = {"/AddFolder"})
public class AddFolder extends HttpServlet {

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
        
            HttpSession session=request.getSession(); 
            String folderName= request.getParameter("foldername");
            Integer parentID = Integer.parseInt(String.valueOf(session.getAttribute("curFolder")));
            User user = (User)session.getAttribute("UserObj");
            
            Folder folder = new Folder(folderName, request.getParameter("folderdesc"), new Folder(parentID),user);
            if(!FolderDAO.checkDuplicateFolder(folder)){
                 FolderDAO.addFolder(folder);
            }
            else{
                session.setAttribute("message", "Folder with same name in the directory exists.");
            }
           
            response.sendRedirect("HomeDocu?fid="+String.valueOf(session.getAttribute("curFolder")));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Adds folder";
    }// </editor-fold>

}
