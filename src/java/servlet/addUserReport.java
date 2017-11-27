/**
 * addUserReport servlet
 * An <b>addUserReport</b> servlet contains the user-related data needed
 * for storing in the database.
 * 
 * @author jen
 * @version 1.001
 * @since 2017-11-25
 */

/* Documentation for developer courtesy of Jennifer Ibay
* orig code: 11/25/17
*/
package servlet;

import dao.Database;
import model.SecurityViolations;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User2Anyone;
import model.User2User;

/**
 *
 * @author Jennifer
 */
@WebServlet(name = "addUserReport", urlPatterns = {"/addUserReport"})
public class addUserReport extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
            
                Connection conn = Database.getDBConnection();
                
                Date date = new Date();
                String boardmemberid = request.getParameter("boardmemberid");
                String securityID = request.getParameter("securityID");
                String complaint = request.getParameter("complaint");
                String resolution = request.getParameter("resolution");
                int statusID = Integer.parseInt(request.getParameter("status"));
                int trxID = Integer.parseInt(request.getParameter("trxID"));
                int violatedpolicyID = Integer.parseInt(request.getParameter("violatedPolicyID"));
                int mappointID = Integer.parseInt(request.getParameter("mappointID"));
                int incidentTypeID = Integer.parseInt(request.getParameter("incidentTypeID"));
                String complainantUserID = request.getParameter("complainantUserID");
                
                // This part checks for the details needed in SecurityViolations. After knowing it, the details will be entered in the database.
                
                SecurityViolations sample = new SecurityViolations(0, date, incidentTypeID, complaint, statusID, resolution, trxID, boardmemberid, securityID, violatedpolicyID, mappointID);
                String sql = "INSERT INTO `hoamis`.`security_violations` (`securityReportID`, `reportDate`, `incidentTypeID`, `complaint`, `status`, `resolution`, `trxID`, `boardmemberID`, `securityID`, `violatedpolicyID`, `mappointID`)" 
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
                try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    request.setAttribute("securitySample", sql);
                    
                    
                    pStmt.setInt(1, sample.getSecurityReportID());
                    pStmt.setString(2, sample.getReportDate());
                    pStmt.setInt(3, sample.getIncidentTypeID());
                    pStmt.setString(4, sample.getComplaint());
                    pStmt.setInt(5, sample.getStatus());
                    pStmt.setString(6, sample.getResolution());
                    pStmt.setInt(7, sample.getTrxID());
                    pStmt.setString(8, sample.getBoardmemberID());
                    pStmt.setString(9, sample.getSecurityID());
                    pStmt.setInt(10, sample.getViolatedpolicyID());
                    pStmt.setInt(11, sample.getMappointID());
                    
                    
                    int isInserted = pStmt.executeUpdate();
                    System.out.println(pStmt);
                    if (isInserted != 0){
                        System.out.println("New Security Report Added!");
                    }
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                
                
                // This part checks for the incident type. After knowing it, the details will be entered in the database.
                // 1 == User&User Incident 
                // 2 == User&OtherParty Incident
                
                if(incidentTypeID == 1){
                try{
                    
                    String accusedUserID = request.getParameter("accusedUserID");
                    User2User u = new User2User(0, complainantUserID, accusedUserID);
                    String sql1 = "INSERT INTO `hoamis`.`user2user` (`securityReportID`, `complainant_userID`, `accused_userID`)" 
                                    + "VALUES (?, ?, ?)";
                    
                    PreparedStatement a = conn.prepareStatement(sql1);
                    request.setAttribute("userSample", sql1);
                   
                    a.setInt(1, u.getSecurityReportID());
                    a.setString(2, u.getComplainantUserID());
                    a.setString(3, u.getAccusedUserID());
                    
                    int isInserted = a.executeUpdate();
                    System.out.println(a);
                    if (isInserted != 0){
                        System.out.println("New Security Report Added!");
                    }
                    
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }}else{
                        try{

                        String otherUserID = request.getParameter("otherParty");
                        User2Anyone u = new User2Anyone(0, complainantUserID, otherUserID);
                        String sql1 = "INSERT INTO `hoamis`.`user2anyone` (`securityReportID`, `userID`, `otherparty`)" 
                                        + "VALUES (?, ?, ?)";

                        PreparedStatement a = conn.prepareStatement(sql1);
                        request.setAttribute("userSample", sql1);

                        a.setInt(1, u.getSecurityReportID());
                        a.setString(2, u.getComplainantID());
                        a.setString(3, u.getOtherParty());

                        int isInserted = a.executeUpdate();
                        System.out.println(a);
                        if (isInserted != 0){
                            System.out.println("New Security Report Added!");
                            
                        }
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
                request.getRequestDispatcher("forwardedUserReport.jsp").forward(request, response);
            }
        }
    


    


