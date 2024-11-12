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

/**
 * Servlet que maneja la creación de empleados.
 * Este servlet permite redirigir al formulario de creación de empleado 
 * y gestionar la creación del mismo mediante solicitudes HTTP GET y POST.
 */
@WebServlet("/crearEmpleado")
public class CrearEmpleadoServlet extends HttpServlet {
    /**
     * Maneja las solicitudes HTTP GET.
     * Redirige al usuario al formulario de creación de empleado.
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
        // Redirigir al formulario de creación de empleado
        request.getRequestDispatcher("views/crearEmpleado.jsp").forward(request, response);
    }

    /**
     * Maneja las solicitudes HTTP POST.
     * Este método crea un nuevo empleado a partir de los datos 
     * proporcionados en el formulario.
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
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anosTrabajados;
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado nuevoEmpleado = new Empleado();
        
        Empleado emp = empleadoDAO.obtenerEmpleadoPorDNI(dni);
        if (emp != null) {
            request.setAttribute("error", "El dni ya esta registrado");
            request.getRequestDispatcher("views/crearEmpleado.jsp").forward(request, response);
            return;
        }

        // Validar el campo de años trabajados
        try {
            anosTrabajados = Integer.parseInt(request.getParameter("anos_trabajados"));
            if (anosTrabajados < 0) {
                request.setAttribute("error", "Los años trabajados deben ser un valor positivo");
                request.getRequestDispatcher("views/crearEmpleado.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Los años trabajados deben ser un número válido.");
            request.getRequestDispatcher("views/crearEmpleado.jsp").forward(request, response);
            return;
        }

        // Validar categoría
        if (categoria < 1 || categoria > 10)  {
            request.setAttribute("error", "Categoría inválida. Debe estar entre 1 y 10");
            request.getRequestDispatcher("views/crearEmpleado.jsp").forward(request, response);
            return;
        }

        // Crear un objeto Empleado
        nuevoEmpleado = new Empleado(dni, nombre, sexo, categoria, anosTrabajados);
        // Guardar el nuevo empleado en la base de datos
        empleadoDAO.agregarEmpleado(nuevoEmpleado); 

        // Redirigir a la lista de empleados tras la creación
        response.sendRedirect("empleados");
    }
}
