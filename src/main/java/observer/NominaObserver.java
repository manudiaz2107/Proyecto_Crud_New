package observer;

import dao.NominaDAO;
import model.Empleado;

public class NominaObserver implements Observer {
    
    private final NominaDAO nominaDAO = NominaDAO.getInstance();
    
    @Override
    public void actualizar(Empleado empleado) {
        // Recalcular el salario
        double nuevoSalario = nominaDAO.calcularNuevoSalario(empleado);
        // Actualizar el salario en la base de datos
        nominaDAO.actualizarSalario(empleado.getDni(), nuevoSalario);

        // Mensaje de registro para verificar que el método se ejecuta
        System.out.println("Observer Notificado: Salario actualizado para el empleado " + empleado.getNombre() + 
                           " (DNI: " + empleado.getDni() + ") a " + nuevoSalario + " €");
    }
}
