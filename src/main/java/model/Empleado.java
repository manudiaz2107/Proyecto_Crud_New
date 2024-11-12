/**
 * La clase Empleado representa un empleado con atributos como DNI, nombre, sexo, 
 * categoría y años trabajados.
 */
package model;

public class Empleado {

    // Atributos privados del empleado
    private String dni;
    private String nombre;
    private String sexo;
    private int categoria;
    private int anosTrabajados;

    /**
     * Constructor con parámetros que inicializa los atributos de un empleado.
     *
     * @param dni El DNI del empleado.
     * @param nombre El nombre del empleado.
     * @param sexo El sexo del empleado.
     * @param categoria La categoría del empleado.
     * @param anosTrabajados Los años trabajados por el empleado.
     */
    public Empleado(String dni, String nombre, String sexo, int categoria, int anosTrabajados) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anosTrabajados = anosTrabajados;
    }

    /**
     * Constructor por defecto.
     */
    public Empleado() {
        // Constructor sin parámetros
    }

    /**
     * Obtiene el DNI del empleado.
     *
     * @return El DNI del empleado.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el sexo del empleado.
     *
     * @return El sexo del empleado.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Obtiene la categoría del empleado.
     *
     * @return La categoría del empleado.
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Obtiene los años trabajados por el empleado.
     *
     * @return Los años trabajados del empleado.
     */
    public int getAnosTrabajados() {
        return anosTrabajados;
    }
}
