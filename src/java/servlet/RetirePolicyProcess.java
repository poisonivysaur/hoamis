package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.User;
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

import dao.Policy;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static others.Validate.*;
import dao.Penalty;
import dao.Document;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 *
 * @author Leebet-PC
 */
@WebServlet(name = "RetirePolicyProcess", urlPatterns = {"/RetirePolicyProcess"})
public class RetirePolicyProcess extends HttpServlet {

  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int policyID, penaltyID, documentID;
        String policyDescription;
        
        policyID = Integer.parseInt(request.getParameter("policy"));        
        
        HttpSession session=request.getSession();  
        User user = (User)session.getAttribute("UserObj");
        String userID = (String)session.getAttribute("userID");
        
        System.out.println(policyID);        

         /**  && Validate.checkUserType(userID, pWord) == 1 **/ 
        if(policyID != 0){            
            
            user.sql_retirePolicy(policyID);
            RequestDispatcher rs = request.getRequestDispatcher("officer/com-mgt/policyMgt/retirepolicy.jsp");
            rs.include(request, response);
            
        }
        else{
            System.out.print(request.getParameter("ERROR!"));
        } 
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
