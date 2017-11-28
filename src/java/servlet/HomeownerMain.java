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
@WebServlet(name = "HomeownerMain", urlPatterns = {"/HomeownerMain"})
public class HomeownerMain extends HttpServlet {
    
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
            if(loginUser.getUsertype() != 1){
                response.sendError(403, "You have no permission for this Page");
            }else{
                if(request.getParameter("action") == null || request.getParameter("action").equals("")){
                    request.getRequestDispatcher("homeowner/dashboard.jsp").forward(request, response);
                }else{
                    String action = request.getParameter("action");
                    String forward = "homeowner/dashboard.jsp";
                    if(action.equals("register")){
                        forward = "homeowner/accounts/registration/registration.jsp";
                    }
                    if(action.equals("dues")){
                        forward = "homeowner/accounts/duesFees/duesHomeHomeowner.jsp";
                    }
                    if(action.equals("duesView")){
                        forward = "homeowner/accounts/duesFees/duesViewHomeowner.jsp";
                    }
                    if(action.equals("duesCont")){
                        forward = "homeowner/accounts/duesFees/duesContHomeowner.jsp";
                    }
                    if(action.equals("duesForm")){
                        forward = "homeowner/accounts/duesFees/duesFormOfficer.jsp";
                    }
                    if(action.equals("duesSuccess")){
                        forward = "homeowner/accounts/duesFees/duesSuccessOfficer.jsp";
                    }
                    if(action.equals("directory")){
                        forward = "homeowner/accounts/directory/directory.jsp";
                    }
                   
                    if(action.equals("billingView")){
                        forward = "homeowner/fin-accounting/billingCollection/billing-view.jsp";
                    }
                    if(action.equals("billingViewDetails")){
                        String userid = request.getParameter("userid");
                        String fname = request.getParameter("fname");
                        String lname = request.getParameter("lname");
                        String mname = request.getParameter("mname");
                        forward = "homeowner/fin-accounting/billingCollection/billing-view-details.jsp?userid="+userid+"&fname="+fname+"&lname="+lname+"&mname="+mname;
                    }
                    
                    if(action.equals("billingTrxDetails")){
                        String userid = request.getParameter("userid");
                        String fname = request.getParameter("fname");
                        String lname = request.getParameter("lname");
                        String mname = request.getParameter("mname");
                        String billingID = request.getParameter("billingID");
                        String totalDue = request.getParameter("totalDue");
                        String totalPaid = request.getParameter("totalPaid");
                        forward = "homeowner/fin-accounting/billingCollection/billing-trx-details.jsp?billingID="+billingID+"&totaldue="+totalDue+"&totalpaid="+totalPaid+"&userid="+userid+"&fname="+fname+"&lname="+lname+"&mname="+mname;
                    }
                    
                    if(action.equals("document")){
                        response.sendRedirect("HomeDocu?fid=" + session.getAttribute("curFolder"));
                        //forward = "homeowner/com-mgt/docuMgt/Home.jsp?fid=" + session.getAttribute("curFolder");
                    }
                    
                    if(action.equals("vehicle")){
                        forward = "homeowner/security/vehicleAdmin/ViewVehicles.jsp";
                    }
                    
                    if(action.equals("allVehicles")){
                        forward = "homeowner/security/vehicleAdmin/ViewAllVehicleRecords.jsp";
                    }
                    
                    if(action.equals("sticker")){
                        forward = "homeowner/security/vehicleAdmin/ViewStickers.jsp";
                    }
                    
                    if(action.equals("addSticker")){
                        forward = "homeowner/security/vehicleAdmin/AddSticker.jsp";
                    }
                    
                    if(action.equals("buySticker")){
                        forward = "homeowner/security/vehicleAdmin/BuySticker.jsp";
                    }
                    
                    if(action.equals("issueSticker")){
                        forward = "homeowner/security/vehicleAdmin/IssueSticker.jsp";
                    }
                    if(action.equals("recordVehicle")){
                        forward = "homeowner/security/vehicleAdmin/RecordVehicle.jsp";
                    }
                    
                    if(!action.equals("document")){
                        request.getRequestDispatcher(forward).forward(request, response);
                    }
                    
                    
                    
                    //request.getRequestDispatcher(forward).forward(request, response);
                }
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
    
    /*
    private String getRedirectByUserType(int userType){
        String redirect = "";
        
        return redirect;
    }
    */
    
}
