/**
 * Este paquete contiene las clases controladoras de la aplicación, 
 * que manejan las solicitudes HTTP y coordinan la interacción entre 
 * la vista y el modelo.
 */
package controller;

import dao.EmpleadoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet que maneja la eliminación de empleados.
 * Este servlet se encarga de recibir solicitudes HTTP POST para eliminar 
 * un empleado a partir de su DNI.
 */
@WebServlet("/eliminarEmpleServlet")
public class EliminarEmpleServlet extends HttpServlet {
    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el servlet y crea una instancia del EmpleadoDAO.
     *
     * @throws ServletException si ocurre un error durante la inicialización del servlet.
     */
    @Override
    public void init() throws ServletException {
        empleadoDAO = new EmpleadoDAO(); // Inicializar DAO
    }

    /**
     * Maneja las solicitudes HTTP POST.
     * Este método elimina un empleado de la base de datos utilizando 
     * el DNI proporcionado en la solicitud.
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
        empleadoDAO.eliminarEmpleado(dni); // Asegúrate de que este método esté implementado en EmpleadoDAO
        response.sendRedirect("empleados"); // Redirigir después de eliminar
    }
}
