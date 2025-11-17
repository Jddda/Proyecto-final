/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amart
 */
@WebServlet(name = "PasajeroControl", urlPatterns = {"/PasajeroControl"})
public class PasajeroControl extends HttpServlet {

   private PasajeroServicio ps = new PasajeroServicio();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cual = request.getParameter("ContPasajero");

        if (cual.equals("registrar")) {
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String metodoPagoPref = request.getParameter("metodoPagoPref");

            Pasajero pasajero = new Pasajero(idUsuario, metodoPagoPref);
            ps.registrarPasajero(pasajero);

            request.setAttribute("mensaje", "El pasajero ha sido registrado exitosamente.");
            request.getRequestDispatcher("/FormPasajero.jsp").forward(request, response);

        } else {
            List<Pasajero> lista = ps.listarPasajeros();
            String lis = "<br>";
            for (Pasajero p : lista) {
                lis += "Pasajero ID Usuario: " + p.getIdUsuario() + " - Método: " + p.getMetodoPagoPref() + "<br>";
            }

            request.setAttribute("mensaje", "Pasajeros registrados: " + lis);
            request.getRequestDispatcher("/ListarTodosPasajeros.jsp").forward(request, response);
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
