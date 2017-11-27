/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.Database;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//db import below
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import model.Users;


/**
 *
 * @author Jennifer Ibay
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        Connection conn = Database.getDBConnection();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Users user = new Users (username, password);
        
        try{
            
            String sql = "SELECT * FROM USERS WHERE userID = '"+ user.getUserID() +"' AND passwd ='" + user.getPassword() + "'";
            
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);
         
            while (rs.next()){
                if(rs.getInt(3)< 4){
                    
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", user);
                
                request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
                }else{
                     
                request.getRequestDispatcher("Loginpage.jsp").forward(request, response);   
                    }
                }
            
        }catch(SQLException e){
            e.printStackTrace();
            
        }
        
        
        
    }
}
