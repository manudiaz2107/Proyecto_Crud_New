/**
 * Este paquete contiene las clases controladoras de la aplicación, 
 * que manejan las solicitudes HTTP y coordinan la interacción entre 
 * la vista y el modelo.
 */
package controller;

import dao.EmpleadoDAO;
import model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet que maneja la visualización de la lista de empleados.
 * Este servlet se encarga de recibir solicitudes HTTP GET y 
 * obtener la lista de empleados desde la base de datos, 
 * redirigiendo a la vista correspondiente.
 */
@WebServlet("/empleados")
public class EmpleadosServlet extends HttpServlet {
    
    /**
     * Maneja las solicitudes HTTP GET.
     * Este método obtiene la lista de empleados de la base de datos 
     * y la envía a la vista empleados.jsp para su presentación.
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> empleados = empleadoDAO.listarEmpleados();

        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
    }
}
