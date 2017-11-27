/**
 * addVehicleReport servlet
 * An <b>addVehicleReport</b> servlet contains the vehicle-related data needed
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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SecurityViolations;
import model.User2Anyone;
import model.User2User;
import model.Vehicle2User;
import model.Vehicle2Vehicle;

/**
 *
 * @author Jennifer
 */
@WebServlet(name = "addVehicleReport", urlPatterns = {"/addVehicleReport"})
public class addVehicleReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                Connection conn = Database.getDBConnection();
               
                Date date = new Date();
                String boardmemberid = request.getParameter("boardmemberid");
                String securityID = request.getParameter("securityID");
                String complaint = request.getParameter("complaint");
                String resolution = request.getParameter("resolution");
                int trxID = Integer.parseInt(request.getParameter("trxID"));
                int statusID = Integer.parseInt(request.getParameter("status"));
                int violatedpolicyID = Integer.parseInt(request.getParameter("violatedPolicyID"));
                int mappointID = Integer.parseInt(request.getParameter("mappointID"));
                int incidentTypeID = Integer.parseInt(request.getParameter("incidentTypeID"));
                
                
                // This part checks for the details needed in SecurityViolations. After knowing it, the details will be entered in the database.
                
                SecurityViolations sample = new SecurityViolations(0, date, incidentTypeID, complaint, statusID, resolution, trxID, boardmemberid, securityID, violatedpolicyID, mappointID);
                String sql3 = "INSERT INTO `hoamis`.`security_violations` (`securityReportID`, `reportDate`, `incidentTypeID`, `complaint`, `status`, `resolution`, `trxID`, `boardmemberID`, `securityID`, `violatedpolicyID`, `mappointID`)" 
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
              
                try{
                    PreparedStatement pStmt = conn.prepareStatement(sql3);
                    request.setAttribute("securitySample", sample);
                    
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
                // 3 == Vehicle&User Incident 
                // 4 == Vehicle&Vehicle Incident
                if(incidentTypeID == 3){
                try{
                    
                    String complainantUserID = request.getParameter("complainantUserID");
                    String accusedplatenum = request.getParameter("accusedplatenum");
                    Vehicle2User u = new Vehicle2User(0, complainantUserID, accusedplatenum);
                    String sql1 = "INSERT INTO `hoamis`.`vehicle2user` (`securityReportID`, `userID`, `platenum`)" 
                                    + "VALUES (?, ?, ?)";
                    
                    PreparedStatement a = conn.prepareStatement(sql1);
                    request.setAttribute("vehicleSample", sql1);
                   
                    a.setInt(1, u.getSecurityReportID());
                    a.setString(2, u.getComplainantUserID());
                    a.setString(3, u.getAccusedplatenum());
                    
                    int isInserted = a.executeUpdate();
                    System.out.println(a);
                    if (isInserted != 0){
                        System.out.println("New Security Report Added!");
                    }
                    
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }}else{
                        try{

                        String complainantplatenum = request.getParameter("complainantplatenum");
                        String accusedplatenum = request.getParameter("accusedplatenum");
                        Vehicle2Vehicle u = new Vehicle2Vehicle(0, complainantplatenum, accusedplatenum);
                        String sql1 = "INSERT INTO `hoamis`.`vehicle2vehicle` (`securityReportID`, `complainantplatenum`, `accusedplatenum`)" 
                                        + "VALUES (?, ?, ?)";

                        PreparedStatement a = conn.prepareStatement(sql1);
                        request.setAttribute("vehicleSample", sql1);

                        a.setInt(1, u.getSecurityReportID());
                        a.setString(2, u.getComplainantplatenum());
                        a.setString(3, u.getAccusedplatenum());

                        int isInserted = a.executeUpdate();
                        System.out.println(a);
                        if (isInserted != 0){
                            System.out.println("New Security Report Added!");
                            
                        }
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
                request.getRequestDispatcher("forwardedvehicleReport.jsp").forward(request, response);
            }
    }
   
