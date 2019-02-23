/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpg.project01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mulero
 */
@WebServlet(name = "CompostoServlet", urlPatterns = {"/composto.html"})
public class CompostoServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlets - Juros Composto</title>");
            out.println("<style> th, td { border: 1px solid black; } </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><a href='index.html'>Servlets</a></h1>");
            out.println("<h2>Juros Composto</h2>");
            out.println("<hr/>");

            out.println("<form>");
            out.println("Digite o número de períodos: <input type='text' name='periodo' placeholder='Número em meses'/>");
            out.println("<br>");
            out.println("<br>");

            out.println("Digite o capital: <input type='text' name='capital' placeholder='0.00'/>");
            out.println("<br>");
            out.println("<br>");

            out.println("Digite a taxa de juros: <input type='text' name='taxa' placeholder='0.0'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("<input type='submit' name='juros' value='Calcular Juros'/>");
            out.println("</form>");

            out.println("<hr/>");

            if (request.getParameter("juros") != null) {
                try {
                    double montante, juros;

                    double capital = Double.parseDouble(request.getParameter("capital"));
                    double taxa = Double.parseDouble(request.getParameter("taxa"));
                    int periodo = Integer.parseInt(request.getParameter("periodo"));

                    taxa /= 100;

                    out.println("<table style='border-collapse: collapse; width: 100%;'>"
                            + "        <thead>"
                            + "            <td> Mês </td>"
                            + "            <td> Capital (R$) </td>"
                            + "            <td> Juros (%) </td>"
                            + "            <td> Montante (R$) </td>"
                            + "        </thead>"
                            + "        <tbody>");

                    for (int i = 1; i <= periodo; i++) {
                        juros = taxa * capital;
                        montante = capital + juros;

                        capital = Math.round(capital * 100.0) / 100.0;
                        juros = Math.round(juros * 100.0) / 100.0;
                        montante = Math.round(montante * 100.0) / 100.0;

                        out.println("<tr>");
                        out.println("<td>" + i + "</td>");
                        out.println("<td>" + capital + "</td>");
                        out.println("<td>" + juros + "</td>");
                        out.println("<td>" + montante + "</td>");
                        out.println("</tr>");

                        capital += juros;

                    }

                    out.println("        </tbody>"
                            + "    </table>");

                } catch (NumberFormatException e) {
                    if (e.getMessage().equals("empty String")) {
                        out.println("<h4 style='color: red'>Campos vazios</h4>");
                    } else {
                        out.println("<h4 style='color: red'>Campos inválidos</h4>");
                    }
                } catch (Exception e) {
                    out.println("<h4 style='color: red'>Erro ao calcular os juros</h4>");
                }
            }

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
