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
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpSession;



import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.*;
import org.apache.commons.io.*;
import model.Document;
import model.User;
import model.Folder;
import model.dao.DocumentDAO;
/**
 *
 * @author Leebet-PC
 */
@WebServlet(name = "AddDocument", urlPatterns = {"/AddDocument"})
public class AddDocument extends HttpServlet {

   
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
        
            HttpSession session=request.getSession(); 
        /*
         *Change the directory. Must be absolute.   
         */
        String UPLOAD_DIRECTORY = "C:\\Users\\Yuta\\Documents\\NetBeansProjects\\hoamisgit\\web\\ourFiles\\uploads";
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for(FileItem item : multiparts){
                if(!item.isFormField()){
                      File a = new File(UPLOAD_DIRECTORY + File.separator + item.getName());
                      String name = a.getName();
                      String loc = UPLOAD_DIRECTORY + File.separator + item.getName();
                      User user = (User)session.getAttribute("UserObj");
                     
                      if (a.exists()){
                          //session.setAttribute("message", "File Already exists");
                      }
                      else{
                         // session.setAttribute("message", "File Uploaded Successfully");
                          item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                      }
                      
                      
                      Document document = new Document(name, loc, new Folder(Integer.parseInt(String.valueOf(session.getAttribute("curFolder")))), user);
                      System.out.println("Document ID: "+document.getDescription());
                      System.out.println("Folder ID: "+document.getFolder().getFolderID());
                      if(DocumentDAO.checkDuplicateDoc(document)){
                          if(evaluateSize(item)){
                              if(name.length() <=45){
                                DocumentDAO.addDocument(document);
                                session.setAttribute("message", "File Uploaded Successfully");
                              }
                              else{
                                session.setAttribute("message", "File Name too long [45 Characters]");
                              }
                          }
                          else{
                            session.setAttribute("message", "File Size Exceeds Limit [100MB]");
                          } 
                      }
                      else{
                            session.setAttribute("message", "File Already exists");
                      }
                    }
                }   //File uploaded successfully
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }         
            response.sendRedirect("HomeDocu?fid="+String.valueOf(session.getAttribute("curFolder")));
        }

    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Adds document";
    }// </editor-fold>
    
    public boolean evaluateSize(FileItem item){
       long MAX_SIZE = 104857600; // 100MB = 100 * 1024 * 1024
        // assume too large
    return item.getSize() <= MAX_SIZE;
}
}
