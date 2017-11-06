/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import DAO.Group;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * A servlet for forwarding group information into group detail page.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for adding accounts.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 * 
 */
@WebServlet(name = "GroupDet", urlPatterns = {"/GroupDet"})
public class GroupDet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String a = request.getParameter("groupname");
        HttpSession session=request.getSession();  
        
        Group g = new Group(a);
        RequestDispatcher rs = request.getRequestDispatcher("groups.jsp");
        
	request.setAttribute("groupDet",g);
        session.setAttribute("userMem", g.getNotMembers(g.getGroup_id()));
        
        rs.include(request, response);
        rs.forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "A servlet for forwarding group information into group detail page.";
    }// </editor-fold>

}
