package org.probandoopenjpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {
    @Id
    private String dni;
    private String nombres;
    private String apellidos;
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    } 
}
