/**
 * EmpleadoDAO es la clase responsable de manejar las operaciones CRUD (Crear, 
 * Leer, Actualizar y Eliminar) relacionadas con la tabla de empleados en la base 
 * de datos. También proporciona métodos para filtrar empleados y gestionar sus 
 * registros en la tabla de nóminas.
 */
package dao;

import model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import conexion.DatabaseConnection;

public class EmpleadoDAO {

    /**
     * Recupera una lista de todos los empleados almacenados en la base de datos.
     * 
     * @return Una lista de objetos Empleado, o null en caso de error.
     */
    public List<Empleado> listarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT dni, nombre, sexo, categoria, anos_trabajados FROM empleados";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre");
                String sexo = resultSet.getString("sexo");
                int categoria = resultSet.getInt("categoria");
                int anosTrabajados = resultSet.getInt("anos_trabajados");

                Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anosTrabajados);
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return empleados;
    }

    /**
     * Busca un empleado en la base de datos por su DNI.
     * 
     * @param dni El DNI del empleado que se desea buscar.
     * @return El objeto Empleado correspondiente si se encuentra, de lo contrario, null.
     */
    public Empleado obtenerEmpleadoPorDNI(String dni) {
        Empleado empleado = null;
        String sql = "SELECT dni, nombre, sexo, categoria, anos_trabajados FROM empleados WHERE dni = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dni);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String sexo = resultSet.getString("sexo");
                int categoria = resultSet.getInt("categoria");
                int anosTrabajados = resultSet.getInt("anos_trabajados");

                empleado = new Empleado(dni, nombre, sexo, categoria, anosTrabajados);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleado;
    }

    /**
     * Actualiza la información de un empleado existente en la base de datos.
     * 
     * @param empleado El objeto Empleado que contiene la información actualizada.
     */
    public void actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anos_trabajados = ? WHERE dni = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getSexo());
            preparedStatement.setInt(3, empleado.getCategoria());
            preparedStatement.setInt(4, empleado.getAnosTrabajados());
            preparedStatement.setString(5, empleado.getDni());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un empleado de la base de datos por su DNI.
     * También elimina la nómina asociada al empleado.
     * 
     * @param dni El DNI del empleado a eliminar.
     */
    public void eliminarEmpleado(String dni) {
        String sql = "DELETE FROM empleados WHERE dni = ?";
        NominaDAO dao = new NominaDAO();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            dao.eliminarNomina(dni);  // Elimina la nómina antes de eliminar el empleado
            preparedStatement.setString(1, dni);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega un nuevo empleado a la base de datos.
     * También inserta una nómina para el nuevo empleado.
     * 
     * @param empleado El objeto Empleado que contiene la información del nuevo empleado.
     */
    public void agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anos_trabajados) VALUES (?, ?, ?, ?, ?)";
        NominaDAO dao = new NominaDAO();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, empleado.getDni());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setString(3, empleado.getSexo());
            preparedStatement.setInt(4, empleado.getCategoria());
            preparedStatement.setInt(5, empleado.getAnosTrabajados());
            preparedStatement.executeUpdate();
            dao.insertarNomina(empleado);  // Inserta la nómina correspondiente
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Filtra empleados según los parámetros proporcionados.
     * 
     * @param sexo      El sexo del empleado (opcional).
     * @param nombre    Una cadena que el nombre del empleado debe contener (opcional).
     * @param categoria La categoría del empleado (opcional).
     * @param anyos     El número de años trabajados por el empleado (opcional).
     * @return Una lista de empleados que cumplen con los filtros especificados.
     */
    public List<Empleado> filtrarEmpleados(String sexo, String nombre, Integer categoria, Integer anyos) {
        List<Empleado> empleados = listarEmpleados();
        return empleados.stream()
                .filter(e -> (sexo == null || sexo.isEmpty() || e.getSexo().equals(sexo))) // Filtra por sexo
                .filter(e -> (nombre == null || nombre.isEmpty() || e.getNombre().toLowerCase().contains(nombre.toLowerCase()))) // Filtra por nombre
                .filter(e -> (categoria == null || e.getCategoria() == categoria)) // Filtra por categoría
                .filter(e -> (anyos == null || e.getAnosTrabajados() == anyos)) // Filtra por años trabajados
                .collect(Collectors.toList());
    }

}
