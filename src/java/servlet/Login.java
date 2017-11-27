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
import model.dao.SecurityAccessDAO;

/**
 *
 * @author Yuta
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
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
            String redirect = this.getRedirectByUserType(loginUser.getUsertype());
            
            response.sendRedirect(redirect);
        }else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
        //PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User tryLogin = new User();
        tryLogin.setUserID(username);
        tryLogin.setPasswd(password);
        
        User loginUser = SecurityAccessDAO.loginUserProcess(tryLogin);
        
        if(loginUser != null){
            session.setAttribute("loginUser", loginUser);
            session.setAttribute("uname", loginUser.getUserID());
            session.setAttribute("users", UserDAO.getUsers(loginUser.getUserID()));
            session.setAttribute("groups", Usergroup.getAllGroups(loginUser.getUserID()));
            session.setAttribute("curFolder", 0);
            session.setAttribute("UserObj", loginUser);
            response.sendRedirect(getRedirectByUserType(loginUser.getUsertype()));
            /*
            out.print("<p>UserId: " + loginUser.getUserID() + "</p>");
            out.print("<p>Fname: " + loginUser.getfName() + "</p>");
            out.print("<p>Lname: " + loginUser.getlName() + "</p>");
            out.print("<p>User Type: " + loginUser.getUserTypeString() + "</p>");
            */
        }else{
            request.setAttribute("msg", "Username or Password is Incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            //out.print("<p>Login Failed</p>");
        }
        
        
    }
    
    /*
     *This method is simply returning the redirect URL.
     */
    /**
     * Handles the User Type URL Redirect.
     *
     * @param userType The user type to be identify the URL
     * @return String containing Redirect URL.
     */
    private String getRedirectByUserType(int userType){
        String redirect = "";
        switch (userType){
            case 1:
                redirect = "HomeownerMain";
                break;
            case 2:
                redirect = "OfficerMain";
                break;
            case 3:
                redirect = "SecurityMain";
                break;
            case 4:
                redirect = "SysadminMain";
                break;
        }
        return redirect;
    }

}
