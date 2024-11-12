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

import dao.EmpleadoDAO;
import model.Empleado;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet que maneja la filtración de empleados.
 * Este servlet recibe solicitudes HTTP GET para filtrar empleados 
 * basándose en parámetros como sexo, nombre, categoría y años de 
 * experiencia.
 */
@WebServlet("/filtrar")
public class FiltrarServlet extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP GET.
     * Este método obtiene los parámetros del formulario de filtrado, 
     * llama al método para filtrar empleados en la base de datos y 
     * redirige a la vista para mostrar los resultados.
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

        // Obtener parámetros del formulario
        String sexo = request.getParameter("sexo");
        String nombre = request.getParameter("nombre");
        String categoriaStr = request.getParameter("categoria");
        String anyosStr = request.getParameter("anyos");
        EmpleadoDAO dao = new EmpleadoDAO();
        Integer categoria = null;
        Integer anyos = null;

        // Manejar los valores numéricos opcionales
        if (categoriaStr != null && !categoriaStr.isEmpty()) {
            categoria = Integer.parseInt(categoriaStr);
        }
        if (anyosStr != null && !anyosStr.isEmpty()) {
            anyos = Integer.parseInt(anyosStr);
        }

        // Filtrar empleados con base en los parámetros obtenidos
        List<Empleado> empleadosFiltrados = dao.filtrarEmpleados(sexo, nombre, categoria, anyos);

        // Pasar la lista de empleados al JSP
        request.setAttribute("empleados", empleadosFiltrados);

        // Reenviar a la página JSP para mostrar los resultados
        request.getRequestDispatcher("views/filtrar.jsp").forward(request, response);
    }
}
