/**
 * La clase Nomina proporciona la lógica para calcular el sueldo de un empleado 
 * basándose en su categoría y años trabajados.
 */
package model;

public class Nomina {
    
    // Sueldo base por categoría, donde el índice corresponde a la categoría del empleado - 1
    private static final int SUELDO_BASE[] = {
        50000, 70000, 90000, 110000, 130000,
        150000, 170000, 190000, 210000, 230000
    };

    /**
     * Calcula el sueldo de un empleado basándose en su categoría y años trabajados.
     *
     * @param empleado El objeto Empleado del cual se va a calcular el sueldo.
     * @return El sueldo calculado para el empleado.
     */
    public static double sueldo(Empleado empleado) {
        // Calcula el sueldo basándose en la categoría y los años trabajados
        return SUELDO_BASE[empleado.getCategoria() - 1] + empleado.getAnosTrabajados() * 5000;
    }
}
