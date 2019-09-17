/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TranslateServlet", urlPatterns = {"/TranslateServlet"})
public class TranslateServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String button, input, source, dest, eng, srb, ger;
        eng = request.getParameter("eng");
        srb = request.getParameter("srb");
        ger = request.getParameter("ger");  
        button = request.getParameter("button");
        input = request.getParameter("input");
        source = request.getParameter("src");
        dest = request.getParameter("dst"); 
        TranslatorImpl ti = new TranslatorImpl();
        try (PrintWriter out = response.getWriter()) {
           // out.print(source + " " + dest+ " "+input +" " +button);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Translate Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            if(source.isEmpty()||dest.isEmpty())
                out.print("You need to choose both languages");
            else{
            switch(button)
            {
                case"button1":
            if(source.equals(dest))
            out.print("You had choosen two same langueges!");
            else
            out.println(ti.translate(input, source, dest));
            break;
                case"button2":
            ti.addWord(eng, srb, ger);
            out.print("<h3>YOU ADDED A NEW WORD: {English: "+eng+", Serbian: "+srb+", German: "+ger+"}.</h3>");
            break;
            }
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
