/**
 * Este paquete contiene las clases controladoras de la aplicación, 
 * que manejan las solicitudes HTTP y coordinan la interacción entre 
 * la vista y el modelo.
 */
package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NominaDAO;

import java.io.IOException;

/**
 * Servlet que maneja la consulta de salarios de empleados.
 * Este servlet recibe solicitudes HTTP POST para obtener el salario 
 * de un empleado basado en su DNI.
 */
@WebServlet("/salario")
public class SalarioServlet extends HttpServlet {
    
    /**
     * Maneja las solicitudes HTTP POST.
     * Este método obtiene el DNI del empleado, consulta su salario 
     * en la base de datos y redirige a la vista para mostrar el 
     * resultado.
     *
     * @param request  objeto HttpServletRequest que contiene la 
     *                 información de la solicitud realizada por el 
     *                 cliente.
     * @param response objeto HttpServletResponse que permite enviar 
     *                 una respuesta al cliente.
     * @throws ServletException si ocurre un error durante el manejo 
     *                          de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        NominaDAO nominaDAO = new NominaDAO();
        double salario = nominaDAO.obtenerSalarioPorDni(dni);

        if (salario > 0) {
            request.setAttribute("dni", dni);
            request.setAttribute("salario", salario);
            request.getRequestDispatcher("views/consultarSalario.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "No se encontró el salario para el DNI: " + dni);
            request.getRequestDispatcher("views/consultarSalario.jsp").forward(request, response);
        }
    }

    /**
     * Maneja las solicitudes HTTP GET.
     * Este método redirige a la vista de consulta de salario, 
     * evitando que se permita un acceso directo a través de GET.
     *
     * @param request  objeto HttpServletRequest que contiene la 
     *                 información de la solicitud realizada por el 
     *                 cliente.
     * @param response objeto HttpServletResponse que permite enviar 
     *                 una respuesta al cliente.
     * @throws ServletException si ocurre un error durante el manejo 
     *                          de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // En lugar de permitir un GET en esta URL, puedes redirigir al formulario
        response.sendRedirect("views/consultarSalario.jsp");
    }
}
