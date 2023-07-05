package org.demo.modelo;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty direccion;
    private final IntegerProperty codigoPostal;
    private final StringProperty ciudad;
    private final ObjectProperty<LocalDate>  cumpleanos;
    
    // Constructor
    public Persona(){
        this(null,null);
    }
    
    // Constructor inicializando datos
    public Persona(String nombre, String apellido){
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty("Alguna direccion");
        this.codigoPostal = new SimpleIntegerProperty(1234);
        this.ciudad = new SimpleStringProperty("Alguna ciudad");
        this.cumpleanos = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }
    
    // Get y set
    public String getNombre(){
        return nombre.get();
    }
    
    public void setNombre(String nombre) {
	this.nombre.set(nombre);
    }
	
    public StringProperty nombreProperty() {
    	return nombre;
    }

    public String getApellido() {
	return apellido.get();
    }

    public void setApellido(String apellido) {
	this.apellido.set(apellido);
    }
    
    public StringProperty apellidoProperty() {
	return apellido;
    }

    public String getDireccion() {
	return direccion.get();
    }

    public void setDireccion(String direccion) {
	this.direccion.set(direccion);
    }
	
    public StringProperty direccionProperty() {
	return direccion;
    }

    public int getCodigoPostal() {
	return codigoPostal.get();
    }

    public void setCodigoPostal(int codigoPostal) {
    	this.codigoPostal.set(codigoPostal);
    }
	
    public IntegerProperty codigoPostalProperty() {
	return codigoPostal;
    }

    public String getCiudad() {
	return ciudad.get();
    }

    public void setCiudad(String ciudad) {
    	this.ciudad.set(ciudad);
    }
	
    public StringProperty ciudadProperty() {
    	return ciudad;
    }

    public LocalDate getCumpleanos() {
    	return cumpleanos.get();
    }

    public void setCumpleanos(LocalDate cumpleanos) {
    	this.cumpleanos.set(cumpleanos);
    }
	
    public ObjectProperty<LocalDate> cumpleanosProperty() {
    	return cumpleanos;
    }
}
