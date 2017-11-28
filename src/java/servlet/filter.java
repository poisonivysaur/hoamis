/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import model.dao.getEstablishments;
import model.dao.getHouses;
import Objects.House;
import Objects.MapPoint;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jayvee Gabriel
 */
@WebServlet(name = "filter", urlPatterns = {"/filter"})
public class filter extends HttpServlet {

    

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
        
        String val = request.getParameter("val");
        ArrayList <MapPoint> mappoints = new ArrayList <MapPoint> ();
        ArrayList <House> houses = new ArrayList <House> ();
        try{
            
            ResultSet rs;
            
            if(val.equals("2")){
                getHouses g1 = new getHouses();
                rs = g1.getHouses();
                
                while(rs.next()){
                    House m1 = new House();
                    m1.setMappointID(rs.getString("mappointID"));
                    m1.setxAxis(rs.getString("xAxis"));
                    m1.setyAxis(rs.getString("yAxis"));
                    m1.setfName(rs.getString("fname"));
                    m1.setlName(rs.getString("lname"));

                    houses.add(m1);
                }
            }
            else{
                getEstablishments g1 = new getEstablishments();
                rs = g1.getEstablishments(val);
                while(rs.next()){
                    MapPoint m1 = new MapPoint();
                    m1.setMappointID(rs.getInt("mappointID"));
                    m1.setxAxis(rs.getString("xAxis"));
                    m1.setyAxis(rs.getString("yAxis"));
                    m1.setTitle(rs.getString("title"));
                    m1.setDescription(rs.getString("description"));

                    mappoints.add(m1);
                }
          }
            
            




        }
        
        
        catch(Exception e){
            System.out.println(e);
        }
        
         StringBuffer aw = new StringBuffer();
        aw.append("<table border = '1'><thead><tr style='width: 75%;'><td style='25%;'><b>Name</b></td><td>View</td></tr></thead><tbody>");
        if(val.equals("2")){
            for(int i = 0; i < houses.size(); i++){
                aw.append("<tr>");
                String id = houses.get(i).getxAxis() + "," + houses.get(i).getyAxis();
                
                aw.append("<td><div style='display: inline-block;'><a href='#'><p id = '" + id + "' >" + houses.get(i).getfName() + " " + houses.get(i).getlName() +  "</p></a></td>");
                aw.append("<td><button type='submit' id='" + id + "' name = 'getDirection' value = '" + id + "'>Get</button></td>");
              
                aw.append("</tr>");
            }
        }
        else{
            for(int i = 0; i < mappoints.size(); i++){

              aw.append("<tr>"); 
              String id = mappoints.get(i).getxAxis() + "," + mappoints.get(i).getyAxis();
              aw.append("<td><div style='display: inline-block;'><a href='#'><p id = '" + id + "' >" + mappoints.get(i).getTitle() +  "</p></a></td>");
              aw.append("<td><button type='submit' id='" + id + "' name = 'getDirection' value = '" + id + "'>Get</button></td>");
              
              aw.append("</tr>");

        }
        }
        
        aw.append("</tbody>");

        
        aw.append("</select>");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(aw.toString());
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
