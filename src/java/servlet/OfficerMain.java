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

/**
 *
 * @author Yuta
 */
@WebServlet(name = "OfficerMain", urlPatterns = {"/OfficerMain"})
public class OfficerMain extends HttpServlet {
    
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser != null){
            if(loginUser.getUsertype() != 2){
                response.sendError(403, "You have no permission for this Page");
            }else{
                if(request.getParameter("action") == null || request.getParameter("action").equals("")){
                    request.getRequestDispatcher("officer/dashboard.jsp").forward(request, response);
                }else{
                    String action = request.getParameter("action");
                    String forward = "officer/dashboard.jsp";
                    if(action.equals("register")){
                        forward = "officer/accounts/registration/registration.jsp";
                    }
                    if(action.equals("dues")){
                        forward = "officer/accounts/duesFees/duesHomeOfficer.jsp";
                    }
                    if(action.equals("duesView")){
                        forward = "officer/accounts/duesFees/duesViewOfficer.jsp";
                    }
                    if(action.equals("duesCont")){
                        forward = "officer/accounts/duesFees/duesContOfficer.jsp";
                    }
                    if(action.equals("duesForm")){
                        forward = "officer/accounts/duesFees/duesFormOfficer.jsp";
                    }
                    if(action.equals("duesSuccess")){
                        forward = "officer/accounts/duesFees/duesSuccessOfficer.jsp";
                    }
                    if(action.equals("directory")){
                        forward = "officer/accounts/directory/directory.jsp";
                    }
                   
                    if(action.equals("billingView")){
                        forward = "officer/fin-accounting/billingCollection/billing-view.jsp";
                    }
                    if(action.equals("billingViewDetails")){
                        String userid = request.getParameter("userid");
                        String fname = request.getParameter("fname");
                        String lname = request.getParameter("lname");
                        String mname = request.getParameter("mname");
                        forward = "officer/fin-accounting/billingCollection/billing-view-details.jsp?userid="+userid+"&fname="+fname+"&lname="+lname+"&mname="+mname;
                    }
                    
                    if(action.equals("billingTrxDetails")){
                        String userid = request.getParameter("userid");
                        String fname = request.getParameter("fname");
                        String lname = request.getParameter("lname");
                        String mname = request.getParameter("mname");
                        String billingID = request.getParameter("billingID");
                        String totalDue = request.getParameter("totalDue");
                        String totalPaid = request.getParameter("totalPaid");
                        forward = "officer/fin-accounting/billingCollection/billing-trx-details.jsp?billingID="+billingID+"&totaldue="+totalDue+"&totalpaid="+totalPaid+"&userid="+userid+"&fname="+fname+"&lname="+lname+"&mname="+mname;
                    }
                    
                    if(action.equals("document")){
                        response.sendRedirect("HomeDocu?fid=" + session.getAttribute("curFolder"));
                        //forward = "officer/com-mgt/docuMgt/Home.jsp?fid=" + session.getAttribute("curFolder");
                    }
                    
                    if(action.equals("vehicle")){
                        forward = "officer/security/vehicleAdmin/ViewVehicles.jsp";
                    }
                    
                    if(action.equals("allVehicles")){
                        forward = "officer/security/vehicleAdmin/ViewAllVehicleRecords.jsp";
                    }
                    
                    if(action.equals("sticker")){
                        forward = "officer/security/vehicleAdmin/ViewStickers.jsp";
                    }
                    
                    if(!action.equals("document")){
                        request.getRequestDispatcher(forward).forward(request, response);
                    }
                    
                    
                    
                    //request.getRequestDispatcher(forward).forward(request, response);
                }
                /*
                out.print("<p>This is OfficerMain.java</p>");
                out.print("<p>First Name:" + loginUser.getfName()  + "</p>");
                out.print("<p>Last Name: " + loginUser.getlName() + "</p>");
                */
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
    
}
