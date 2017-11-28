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
@WebServlet(name = "SecurityMain", urlPatterns = {"/SecurityMain"})
public class SecurityMain extends HttpServlet {

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
            if(loginUser.getUsertype() != 3){
                response.sendError(403, "You have no permission for this Page");
            }else{
                if(request.getParameter("action") == null || request.getParameter("action").equals("")){
                    request.getRequestDispatcher("security/dashboard.jsp").forward(request, response);
                }else{
                    String action = request.getParameter("action");
                    String forward = "security/dashboard.jsp";
                    if(action.equals("register")){
                        forward = "security/accounts/registration/registration.jsp";
                    }
                    
                    if(action.equals("vehicle")){
                        forward = "security/vehicleAdmin/ViewVehicles.jsp";
                    }
                    
                    if(action.equals("allVehicles")){
                        forward = "security/vehicleAdmin/ViewAllVehicleRecords.jsp";
                    }
                    
                    if(action.equals("sticker")){
                        forward = "security/vehicleAdmin/ViewStickers.jsp";
                    }
                    
                    if(action.equals("addSticker")){
                        forward = "security/vehicleAdmin/AddSticker.jsp";
                    }
                    
                    if(action.equals("buySticker")){
                        forward = "security/vehicleAdmin/BuySticker.jsp";
                    }
                    
                    if(action.equals("issueSticker")){
                        forward = "security/vehicleAdmin/IssueSticker.jsp";
                    }
                    if(action.equals("recordVehicle")){
                        forward = "security/vehicleAdmin/RecordVehicle.jsp";
                    }
					
					if(action.equals("security")){
                        forward = "security/reportsViolationAdmin/homepageSecurityRep.jsp";
                    }
                    
                    
                    request.getRequestDispatcher(forward).forward(request, response);
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
    
}
