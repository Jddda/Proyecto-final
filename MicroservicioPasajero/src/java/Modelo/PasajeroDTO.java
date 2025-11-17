/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author amart
 */
public class PasajeroDTO {
    private int idPasajero;
    private String nombre;
    private String apellido;
    private String correo;
    private String identificacion;
    private String telefono;

    public PasajeroDTO() {
    }

    
    public PasajeroDTO(int idPasajero, String nombre, String apellido, String correo, String identificacion, String telefono) {
        this.idPasajero = idPasajero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "PasajeroDTO{" + "idPasajero=" + idPasajero + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", identificacion=" + identificacion + ", telefono=" + telefono + '}';
    }

    

    public int getIdPasajero() {
        return idPasajero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    
}
