package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ref_MonthlyDues;
import model.dao.MonthlyDuesDAO;

/**
 *
 * @author Anne Charlene Cipres
 */
@WebServlet(name = "duesOfficerServlet", urlPatterns = {"/duesOfficerServlet"})
public class duesOfficerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String start = request.getParameter("startMonth");
        String end = request.getParameter("endMonth");
        int startMonth = Integer.parseInt(start);
        int endMonth = Integer.parseInt(end);
        
        start = request.getParameter("startYear");
        end = request.getParameter("endYear");
        int startYear = Integer.parseInt(start);
        int endYear = Integer.parseInt(end);
        
        RequestDispatcher rd = null;
        MonthlyDuesDAO mdd = new MonthlyDuesDAO();
        if(mdd.isOverlappingWithStoredDues(startMonth, startYear, endMonth, endYear)){
            request.setAttribute("message", "This date range is overlapping with one of the date ranges already registered. Please choose another range.");
            rd = request.getRequestDispatcher("officer/accounts/duesFees/duesFormOfficer.jsp");
            rd.forward(request, response);
        }
        
        double amountApproved = Double.parseDouble(request.getParameter("amount"));
        
        if(amountApproved < 1){
            request.setAttribute("message", "Input an amount greater than 0");
            rd = request.getRequestDispatcher("duesFormOfficer.jsp");
            rd.forward(request, response);
        }
        
        Ref_MonthlyDues rmd = new Ref_MonthlyDues(0, startMonth, startYear, endMonth, endYear, amountApproved);
        request.setAttribute("rmdObj", rmd);
        
        if(request.getParameter("submit") != null)
            rd = request.getRequestDispatcher("officer/accounts/duesFees/duesContOfficer.jsp");
        else if(request.getParameter("submit2") != null){
            double amountPerMonth = Double.parseDouble(request.getParameter("monthDues"));
            try {
                mdd.insertMonthlyDues(rmd, amountPerMonth);
                rd = request.getRequestDispatcher("officer/accounts/duesFees/duesSuccessOfficer.jsp");
            } catch (Exception ex) {
                System.out.println("Unable to insert");
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
            
        rd.forward(request, response);
    }

}
