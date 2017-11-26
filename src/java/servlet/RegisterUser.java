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
import model.dao.RegistrationDAO;

/**
 *
 * @author Yuta
 */
@WebServlet(name = "RegisterUser", urlPatterns = {"/RegisterUser"})
public class RegisterUser extends HttpServlet {
    
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
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser != null){
            response.sendRedirect("OfficerMain?action=register");
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
            throws ServletException, IOException {
        int userType = Integer.parseInt(request.getParameter("userTypes"));
        String username = request.getParameter("username");
        String fname = request.getParameter("fName");
        String lname = request.getParameter("lName");
        String mname = request.getParameter("mName");
        String birthday = request.getParameter("bDay");
        String occupation = "";
        occupation = request.getParameter("occupation");
        
        int blocknum = 0;
        int lotnum = 0;
        
        if(request.getParameter("block") != null){
            blocknum = Integer.parseInt(request.getParameter("block"));
            lotnum = Integer.parseInt(request.getParameter("lot"));
        }
        
        int occupationId = -1;
        
        
        if(occupation.equals("others")){
            occupationId = RegistrationDAO.insertNewOccupation(request.getParameter("otherOccupation"));
        }else{
            occupationId = Integer.parseInt(request.getParameter("occupation"));
        }
        
        //System.out.println("Occupation: " + occupationId);
        
        if(userType == 1 || userType == 2 || userType == 3 || userType == 4){
            System.out.println("System Users");
            String password = request.getParameter("password");
            User newUser = new User();
            newUser.setUserID(username);
            newUser.setPasswd(password);
            newUser.setfName(fname);
            newUser.setlName(lname);
            newUser.setmName(mname);
            newUser.setUsertype(userType);
            newUser.statusManager("registration");
            
            if(RegistrationDAO.insertNewSystemUser(newUser, birthday, occupationId, blocknum, lotnum)){
                request.getSession().setAttribute("msg", "Success : New User successfully Registered!!");
            }else{
                request.getSession().setAttribute("msg", "Error : Username or Block & Lot Number has already been used...");
            }
            response.sendRedirect("OfficerMain?action=register");
            
        }else if(userType == 5 || userType == 6){
            System.out.println("Home mebmers or Kasambahay");
            User newNormalUser = new User();
            newNormalUser.setUserID(username);
            newNormalUser.setfName(fname);
            newNormalUser.setlName(lname);
            newNormalUser.setmName(mname);
            newNormalUser.setUsertype(userType);
            newNormalUser.statusManager("registration");
            if(RegistrationDAO.insertNormalUser(newNormalUser, birthday, occupationId, blocknum, lotnum)){
                //System.out.println("SUCCESS");
                request.getSession().setAttribute("msg", "Success : New User successfully Registered!!");
            }else{
                //System.out.println("ERROR");
                request.getSession().setAttribute("msg", "Error : Username or Block & Lot Number has already been used...");
            }
            response.sendRedirect("OfficerMain?action=register");
        }
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
