/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Tools.Artist;
import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.net.URL;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hiroshi
 */
@WebServlet(name = "busca", urlPatterns = {"/busca"})
public class busca extends HttpServlet {

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
            try {
                Boolean found = false;
                String resul = "", nome = "", playcount = "", listener = "", url = "", resultado = "";
                String chave = request.getParameter("chave");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(new URL("http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&limit=10&api_key=917a49b4c9325caecc0bec862aae4397").openStream());
                Element root = document.getDocumentElement();
                NodeList n1 = root.getElementsByTagName("artist");

                for (int i = 0; i < n1.getLength(); i++) {
                    Element element1 = (Element) n1.item(i);
                    nome = element1.getElementsByTagName("name").item(0).getTextContent();
                    if (nome.compareToIgnoreCase(chave) == 0) {
                        found = true;
                        playcount = element1.getElementsByTagName("playcount").item(0).getTextContent();
                        listener = element1.getElementsByTagName("listeners").item(0).getTextContent();
                        url = element1.getElementsByTagName("url").item(0).getTextContent();
                        resultado += "<div>";
                        resultado += "<p>Nome: " + nome + "</p>";
                        resultado += "<p>PlayCount: " + playcount + "</p>";
                        resultado += "<p>Listeners: " + listener + "</p>";
                        resultado += "<a href="+url+">URL</a>";
                        resultado += "</div>";
                        out.println(resultado);
                    }
                }
                if (!found) {
                    out.println("Não encontrado");
                }

            } catch (Exception e) {
                out.println("ERROR");
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
