package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

import model.User;
import model.dao.Policy;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servlet.Validate.*;
import model.dao.Penalty;
import model.dao.Document;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 *
 * @author Leebet-PC
 */
@WebServlet(name = "AddPolicyProcess", urlPatterns = {"/AddPolicyProcess"})
public class AddPolicyProcess extends HttpServlet {

  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int policyID, penaltyID, documentID;
        String policyDescription;
        
        policyID = Integer.parseInt(request.getParameter("policyID"));        
        policyDescription = (request.getParameter("description"));
        penaltyID = Integer.parseInt(request.getParameter("penalty"));
        documentID = Integer.parseInt(request.getParameter("document"));     
        
        HttpSession session=request.getSession();  
        User user = (User)session.getAttribute("UserObj");
        String userID = (String)session.getAttribute("userID");
        
        System.out.println(policyID);
        System.out.println(policyDescription);
        System.out.println(penaltyID);
        System.out.println(documentID);
        System.out.println(userID);

         /**  && Validate.checkUserType(userID, pWord) == 1 **/ 
        if(policyID != 0 && (policyDescription.length() > 0) && penaltyID != 0 && documentID != 0){
            user.sql_createPolicy(policyID, policyDescription, documentID, penaltyID, userID);
            RequestDispatcher rs = request.getRequestDispatcher("officer/com-mgt/policyMgt/addpolicy.jsp");
            rs.include(request, response);
            
        }
        else{
            System.out.print(request.getParameter("ERROR: "));
            System.out.print(request.getParameter("description" + " "));
            System.out.print(request.getParameter("penalty" + " "));
            System.out.print(request.getParameter("document"));
        } 
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
