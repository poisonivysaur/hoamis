/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import model.dao.User;

import model.dao.Group;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.http.*;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * A servlet for processing the adding of user groups.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for adding groups.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */
@WebServlet(name = "AddGroup", urlPatterns = {"/AddGroup"})
public class AddGroup extends HttpServlet {

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session=request.getSession();  
            User user = new User();
            Group g = new Group();
            
            String groupname = request.getParameter("groupname");
            String username = request.getParameter("uname");
            String settings = request.getParameter("settings");
            String[] members = request.getParameterValues("members");
            String[] groups = request.getParameterValues("groups");
            boolean a = Group.AddGroup(groupname, username, members,groups,Integer.parseInt(settings));
            
            // setting sessions
            user.setUser_id(user.getUserID(request.getParameter("uname")));
            user.setUsername(request.getParameter("uname"));
            session.setAttribute("groups", g.getAllGroups(user.getUser_id()));
            response.sendRedirect("Home.jsp");
           
                
    }

 
    @Override
    public String getServletInfo() {
        return "A servlet for processing the adding of user groups.";
    }// </editor-fold>

}
