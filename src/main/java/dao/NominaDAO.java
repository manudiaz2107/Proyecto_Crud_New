/**
 * NominaDAO es responsable de manejar las operaciones relacionadas con las nóminas 
 * de los empleados, tales como obtener, insertar, actualizar y eliminar nóminas en 
 * la base de datos. También proporciona métodos para calcular el salario de un empleado.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DatabaseConnection;
import model.Empleado;
import model.Nomina;

public class NominaDAO {
	private static NominaDAO instancia;

	public static NominaDAO getInstance() {
		if (NominaDAO.instancia == null) {
			return new NominaDAO();
		} else {
			return instancia;
		}
	}

    /**
     * Obtiene el salario de un empleado dado su DNI.
     *
     * @param dni El DNI del empleado.
     * @return El salario del empleado. Si no se encuentra, retorna 0.0.
     */
    public double obtenerSalarioPorDni(String dni) {
        double salario = 0.0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT n.salario FROM nominas n JOIN empleados e ON n.dni = e.dni WHERE e.dni = ?")) {
            preparedStatement.setString(1, dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                salario = resultSet.getDouble("salario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salario;
    }

    /**
     * Calcula el nuevo salario de un empleado basado en su información.
     *
     * @param emp El objeto Empleado con los datos del empleado.
     * @return El nuevo salario calculado.
     */
    public double calcularNuevoSalario(Empleado emp) {
        return Nomina.sueldo(emp);
    }

    /**
     * Actualiza el salario de un empleado en la base de datos.
     *
     * @param dni El DNI del empleado cuyo salario se va a actualizar.
     * @param nuevoSalario El nuevo salario que se va a establecer.
     */
    public void actualizarSalario(String dni, double nuevoSalario) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE nominas SET salario = ? WHERE dni = ?")) {
            preparedStatement.setDouble(1, nuevoSalario);
            preparedStatement.setString(2, dni);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas Afectadas: " + rowsAffected); // Debe ser 1 si se actualizó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta una nueva nómina para un empleado en la base de datos.
     *
     * @param empleado El objeto Empleado con la información del empleado.
     */
    public void insertarNomina(Empleado empleado) {
        String sql2 = "INSERT INTO nominas (dni, salario) VALUES (?, ?)";
        double sueldo = calcularNuevoSalario(empleado);
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
            preparedStatement.setString(1, empleado.getDni());
            preparedStatement.setDouble(2, sueldo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina la nómina de un empleado en la base de datos basado en su DNI.
     *
     * @param dni El DNI del empleado cuya nómina será eliminada.
     */
    public void eliminarNomina(String dni) {
        String sql = "DELETE FROM nominas WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, dni);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
