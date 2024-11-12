package controller;

import dao.EmpleadoDAO;
import dao.NominaDAO;
import model.Empleado;
import model.Nomina;
import observer.NominaObserver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/front") 
public class FrontControllerServlet extends HttpServlet {

    private NominaDAO nominaDAO = NominaDAO.getInstance();
    private final EmpleadoDAO empleadoDAO = EmpleadoDAO.getInstance();
    
    @Override
    public void init() {
        // Registrar el observador
        empleadoDAO.agregarObserver(new NominaObserver());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String option = request.getParameter("option");

        switch (option) {
            case "/":
                // Redirige a la página de inicio
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "empleados":
                listarEmpleados(request, response);
                break;
            case "crearEmpleado":
                request.getRequestDispatcher("/views/crearEmpleado.jsp").forward(request, response);
                break;
            case "modificar":
                mostrarFormularioModificar(request, response);
                break;
            case "filtrar":
                filtrarEmpleados(request, response);
                break;
            case "salario":
                mostrarSalarioEmpleado(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String option = request.getParameter("option");

        switch (option) {
            case "crearEmpleado":
                crearEmpleado(request, response);
                break;
            case "eliminarEmple":
                eliminarEmpleado(request, response);
                break;
            case "modificar":
                modificarEmpleado(request, response);
                break;
            case "calcularSalario":
                calcularSalario(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empleado> empleados = empleadoDAO.listarEmpleados();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/views/empleados.jsp").forward(request, response);
    }

    private void crearEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anosTrabajados = Integer.parseInt(request.getParameter("anos_trabajados"));

        Empleado nuevoEmpleado = new Empleado(dni, nombre, sexo, categoria, anosTrabajados);
        empleadoDAO.agregarEmpleado(nuevoEmpleado);

        // Redirigir a la lista de empleados
        response.sendRedirect("front?option=empleados");
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        empleadoDAO.eliminarEmpleado(dni);
        response.sendRedirect("front?option=empleados");
    }

    private void mostrarFormularioModificar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorDNI(dni);
        if (empleado != null) {
            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("/views/modificar.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Empleado no encontrado");
        }
    }

    private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anosTrabajados = Integer.parseInt(request.getParameter("anos_trabajados"));

        Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anosTrabajados);
        empleadoDAO.actualizarEmpleado(empleado);

        // Recalcular el salario
        double nuevoSalario = nominaDAO.calcularNuevoSalario(empleado);
        nominaDAO.actualizarSalario(dni, nuevoSalario);

        response.sendRedirect("front?option=empleados");
    }

    private void filtrarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sexo = request.getParameter("sexo");
        String nombre = request.getParameter("nombre");
        String categoriaStr = request.getParameter("categoria");
        String anyosStr = request.getParameter("anyos");

        Integer categoria = null;
        Integer anyos = null;
        if (categoriaStr != null && !categoriaStr.isEmpty()) {
            categoria = Integer.parseInt(categoriaStr);
        }
        if (anyosStr != null && !anyosStr.isEmpty()) {
            anyos = Integer.parseInt(anyosStr);
        }

        List<Empleado> empleadosFiltrados = empleadoDAO.filtrarEmpleados(sexo, nombre, categoria, anyos);
        request.setAttribute("empleados", empleadosFiltrados);
        request.getRequestDispatcher("/views/filtrar.jsp").forward(request, response);
    }

    private void mostrarSalarioEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        double salario = nominaDAO.obtenerSalarioPorDni(dni);
        
        request.setAttribute("salario", salario);
        request.getRequestDispatcher("/views/consultarSalario.jsp").forward(request, response);
    }
    
    private void calcularSalario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorDNI(dni);

        if (empleado != null) {
            double salario = nominaDAO.calcularNuevoSalario(empleado);
            request.setAttribute("dni", dni);  // Agregar el DNI para pasarlo al JSP
            request.setAttribute("salario", salario);  // Pasar el salario al JSP
            request.getRequestDispatcher("/views/consultarSalario.jsp").forward(request, response);
        } else {
        	request.setAttribute("error", "No se encontró el salario para el DNI: " + dni);
            request.getRequestDispatcher("views/consultarSalario.jsp").forward(request, response);
        }
    }
}
