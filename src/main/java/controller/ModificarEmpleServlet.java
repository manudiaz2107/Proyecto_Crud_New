package controller;

import model.Empleado;
import dao.EmpleadoDAO;
import dao.NominaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modificar")
public class ModificarEmpleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorDNI(dni);

        if (empleado != null) {
            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("views/modificar.jsp").forward(request, response);
        } else {
            // Maneja el caso en que no se encuentre el empleado
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Empleado no encontrado");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anosTrabajados = Integer.parseInt(request.getParameter("anos_trabajados"));

        Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anosTrabajados);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        // Actualizar los datos del empleado en la base de datos
        empleadoDAO.actualizarEmpleado(empleado);

        // Recalcular el salario basándose en los años trabajados y la categoría
        NominaDAO nominaDAO = new NominaDAO();
        double nuevoSalario = nominaDAO.calcularNuevoSalario(empleado);

        // Actualizar el salario en la tabla de nóminas
        nominaDAO.actualizarSalario(dni, nuevoSalario);

        // Redirigir a la lista de empleados tras la modificación
        response.sendRedirect("empleados");
    }
}

