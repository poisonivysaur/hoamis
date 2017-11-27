/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.FileInputStream;

import java.io.File;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leebet-PC
 */
@WebServlet(name = "DownloadDocument", urlPatterns = {"/DownloadDocument"})
public class DownloadDocument extends HttpServlet {

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

        HttpSession session = request.getSession();
        System.out.println("HEY CAN U DL ME??");
        File f = new File(request.getParameter("DL"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String filename = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1);

        System.out.println(filename);

        /*
         *Change this to absolute path.
         */
        String filepath = "C:\\Users\\Yuta\\Documents\\NetBeansProjects\\hoamisgit\\web\\ourFiles\\uploads\\";

        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\\\"" + filename + "\\\"");

        // use inline if you want to view the content in browser, helpful for
        // pdf file
        // response.setHeader("Content-Disposition","inline; filename=\"" +
        // filename + "\"");
        FileInputStream fileInputStream = new FileInputStream(filepath
                + filename);

        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();

        response.sendRedirect("HomeDocu?fid=" + String.valueOf(session.getAttribute("curFolder")));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for downloading document";
    }// </editor-fold>

}
