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
import model.*;
import model.dao.DocumentDAO;
import model.dao.FolderDAO;

/**
 *
 * @author Leebet-PC
 */
@WebServlet(name = "ShareDocument", urlPatterns = {"/ShareDocument"})
public class ShareDocument extends HttpServlet {


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
        
            HttpSession session=request.getSession();
            String group = request.getParameter("groups");
            
            String[] permissions = request.getParameterValues("permissions");
            
            model.User user = (User)session.getAttribute("UserObj");
           
            Byte r = new Byte("0");
            Byte u = new Byte("0");
            Byte d = new Byte("0");
            for(String permission:permissions){
                switch(permission){
                    case "read":
                        r = new Byte("1");
                    break;
                    case "update":
                        u = new Byte("1");
                    break;
                    case "delete":
                        d = new Byte("1");
                    break;
                }
            }
            if(request.getParameter("fileType").equals("document")){
                System.out.println("File is a Document with docID: "+request.getParameter("fileId"));
                String docid = request.getParameter("fileId");
                Document document = new Document(Integer.parseInt(docid));
                System.out.println("read: "+r);
                System.out.println("update: "+u);
                System.out.println("delete: "+d);
                
                DocumentDAO.shareDocument(document, Integer.parseInt(group), r, u, d); 
                
            }
            else{
                System.out.println("File is a Document with docID: "+request.getParameter("fileId"));
                
                String folderid = request.getParameter("fileId");
                System.out.println("read: "+r);
                System.out.println("update: "+u);
                System.out.println("delete: "+d);
                
                FolderDAO.shareFolder(Integer.parseInt(folderid), Integer.parseInt(group), r, u, d); 
            }
            response.sendRedirect("HomeDocu?fid="+String.valueOf(session.getAttribute("curFolder")));
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
