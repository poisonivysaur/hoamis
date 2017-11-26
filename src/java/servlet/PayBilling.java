/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.BillingDAO;

/**
 *
 * @author Robin
 */
@WebServlet(name = "PayBilling", urlPatterns = {"/PayBilling"})
public class PayBilling extends HttpServlet {

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
        
        // PAY BILLING TO DO: make method in BillingDAO to insert totalPaid in Billing ,int journalID first, then trxList, then payment details
        boolean isPaid = BillingDAO.payBilling(Integer.parseInt(request.getParameter("billID")));// this returns true for now
        request.setAttribute("isPaid", isPaid+"");
        String userid = request.getParameter("userid");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String mname = request.getParameter("mname");
        System.out.println(request.getContextPath());
        RequestDispatcher rd = request.getRequestDispatcher("officer/fin-accounting/billingCollection/billing-view-details.jsp?userid="+userid+"&fname="+fname+"&lname="+lname+"&mname="+mname);
        rd.forward(request, response);
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
