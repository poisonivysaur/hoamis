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
@WebServlet(name = "UserRegistration", urlPatterns = {"/UserRegistration"})
public class UserRegistration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserRegistration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserRegistration at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            throws ServletException, IOException {}

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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        if(action.equals("userRegister")){
            int userType = Integer.parseInt(request.getParameter("userTypes"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("fName");
            String lastName = request.getParameter("lName");
            String middleName = request.getParameter("mName");
            String birthDay = request.getParameter("bDay");
            String occupationId = request.getParameter("occupation");
            int occupation = -1;
            if(occupationId.equals("others")){
                String otherOccupation = request.getParameter("otherOccupation");
                occupation = RegistrationDAO.insertNewOccupation(otherOccupation);
            }else{
                occupation = Integer.parseInt(occupationId);
            }
            /*
            System.out.println("User Type: " + userType);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Firstname: " + firstName);
            System.out.println("Lastname: " + lastName);
            System.out.println("Middlename: " + middleName);
            System.out.println("Birthday: " + birthDay);
            System.out.println("Occupation: " + occupation);
            */
            User newUser = new User();
            newUser.setUserID(username);
            newUser.setPasswd(password);
            newUser.setfName(firstName);
            newUser.setlName(lastName);
            newUser.setmName(middleName);
            newUser.setUsertype(userType);
            String message = "Error:Uncaught Exception";
            switch(userType){
                case 1:
                    String blocklot = request.getParameter("blockLot");
                    int block = Integer.parseInt(blocklot.split(",")[0]);
                    int lot = Integer.parseInt(blocklot.split(",")[1]);
                    if(RegistrationDAO.insertNewHomeowner(newUser, birthDay, occupation, block, lot)){
                        message = "Success:New Homeowner Registered!";
                    }else{
                        message = "Error:Unable to Add New User.";
                    }
                    break;
                case 2:
                    break;
                case 3:
                    if(RegistrationDAO.insertNewSecurity(newUser, birthDay, occupation)){
                        message = "Success:New Security Personnel Added!";
                    }else{
                        message = "Error:Unable to Add New User.";
                    }
                    break;
                case 4:
                    if(RegistrationDAO.insertNewSystemAdmin(newUser, birthDay, occupation)){
                        message = "Success:New System Administrator Added!";
                    }else{
                        message = "Error:Unable to Add New User.";
                    }
                    break;
                case 5:
                    String hmBlockLot = request.getParameter("homeowner");
                    int block1 = Integer.parseInt(hmBlockLot.split(",")[0]);
                    int lot1 = Integer.parseInt(hmBlockLot.split(",")[1]);
                    System.out.println("Homemember:Block Num, Lot Num: " + hmBlockLot);
                    if(RegistrationDAO.insertHomeMember(newUser, birthDay, occupation, block1, lot1)){
                        message = "Success:New Home Member Added!";
                    }else{
                        message = "Error:Unable to Add New User.";
                    }
                    break;
                case 6:
                    String kBlockLot = request.getParameter("homeowner");
                    int block2 = Integer.parseInt(kBlockLot.split(",")[0]);
                    int lot2 = Integer.parseInt(kBlockLot.split(",")[1]); 
                    if(RegistrationDAO.insertKasambahay(newUser, birthDay, occupation, block2, lot2)){
                        message = "Success:New Kasambahay Added!";
                    }else{
                        message = "Error:Unable to Add New User.";
                    }
                    System.out.println("Kasambahay:Block Num, Lot Num: " + kBlockLot);
                    break;
            }

            session.setAttribute("msg", message);
        }//if
        else if(action.equals("mapPoint")){
            System.out.println("MAPPING");
        }
        
        response.sendRedirect("OfficerMain?action=register");
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
