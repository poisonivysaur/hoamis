/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
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
 * @author justine
 */
@WebServlet(name = "duesViewServlet", urlPatterns = {"/duesViewServlet"})
public class duesViewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String temp = request.getParameter("monthlys");
        int mduesID = Integer.parseInt(temp);
        System.out.println("DUMADAAN");
        
        MonthlyDuesDAO mdd = new MonthlyDuesDAO();
        Ref_MonthlyDues rmd = mdd.getRefMonthlyDues(mduesID);
        
        RequestDispatcher rd = null;
        
        request.setAttribute("rmdObj", rmd);
        rd = request.getRequestDispatcher("duesViewOfficer.jsp");
        rd.forward(request, response);
    }
}
