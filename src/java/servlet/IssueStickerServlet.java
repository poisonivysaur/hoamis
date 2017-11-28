package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.dao.DatabaseUtils;
import model.Sticker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Patrisha
 */
@WebServlet(urlPatterns = {"/IssueStickerServlet"})
public class IssueStickerServlet extends HttpServlet {

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
            out.println("<title>Servlet BuyStickerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyStickerServlet at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException {
        processRequest(request, response);
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
        
        Connection conn = DatabaseUtils.retrieveConnection();
       
        String stickers = request.getParameter("stickers");
        
        String sql = "UPDATE user_vehicles SET stickerID = "+stickers;
        String sql3 = "UPDATE stickerInventory SET datereleased = CURDATE() WHERE stickerID = "+stickers;
        
        try{            
            PreparedStatement pStmt = conn.prepareStatement(sql);
           
            PreparedStatement pStmt3 = conn.prepareStatement(sql3);
            
                int isUpdated = pStmt.executeUpdate();
                
                int isUpdated3 = pStmt3.executeUpdate();
                
                if (isUpdated3 != 0){
                    System.out.println("Updated!");
            }else{
                System.out.println("Not Updated");
            }
                
                if (isUpdated != 0){
                    System.out.println("Paid!");
            }else{
                System.out.println("Insufficient Funds");
            }
            
       
            
           
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){
            }
            /*ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                System.out.println("Student ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Course: " + rs.getString(3));
                System.out.println("Year: " + rs.getInt(4));
                System.out.println("");
            }*/
        
            }
        }
        request.getRequestDispatcher("officer/security/vehicleAdmin/IssueSticker.jsp").forward(request, response);
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
