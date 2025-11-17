/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Presentacion;

import DAO.FabricaDAO;
import DAO.FabricaDAOPasajero;
import DAO.PasajeroDAOPostgre;
import Modelo.PasajeroDTO;
import PasajeroServicio.PasajeroServicio;
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

    String accion = request.getParameter("accion");

    // ---------------------------------------------------------
    // REGISTRAR PASAJERO
    // ---------------------------------------------------------
    if ("registrar".equalsIgnoreCase(accion)) {

        int idPasajero = Integer.parseInt(request.getParameter("idPasajero"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String correo = request.getParameter("correo");
            String identificacion = request.getParameter("identificacion");
            String telefono = request.getParameter("telefono");

        // Crear DTO
        PasajeroDTO pasajero = new PasajeroDTO(
                    idPasajero,
                    nombre,
                    apellido,
                    correo,
                    identificacion,
                    telefono
            );

        // Obtener DAO desde la fábrica
        FabricaDAO fabrica = new FabricaDAOPasajero();
        PasajeroDAOPostgre dao = (PasajeroDAOPostgre) fabrica.crearPasajeroDAO("POSTGRE");

        int res = dao.insertarPasajero(pasajero);

        if (res > 0) {
            request.setAttribute("mensaje", "Pasajero registrado correctamente.");
        } else {
            request.setAttribute("mensaje", "ERROR: No se pudo registrar al pasajero.");
        }

        request.getRequestDispatcher("/FormPasajero.jsp").forward(request, response);
    }

    // ---------------------------------------------------------
    // LISTAR TODOS LOS PASAJEROS
    // ---------------------------------------------------------
    else if ("listar".equalsIgnoreCase(accion)) {

        FabricaDAO fabrica = new FabricaDAOPasajero();
        PasajeroDAOPostgre dao = (PasajeroDAOPostgre) fabrica.crearPasajeroDAO("POSTGRE");

        List<PasajeroDTO> lista = dao.listarTodos();

        request.setAttribute("listaPasajeros", lista);
        request.getRequestDispatcher("/ListarTodosPasajeros.jsp").forward(request, response);
    }

    // ---------------------------------------------------------
    // ACCIÓN DESCONOCIDA
    // ---------------------------------------------------------
    else {
        response.getWriter().write("Acción no válida.");
    }
}

    // GET para pruebas
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
