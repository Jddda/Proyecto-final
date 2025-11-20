/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PasajeroServicio;

import DAO.FabricaDAO;
import DAO.FabricaDAOPasajero;
import DAO.PasajeroDAO;
import DAO.PasajeroDAOPostgre;
import Modelo.PasajeroDTO;
import java.util.List;

public class PasajeroServicio {
    
    private FabricaDAO fabrica;
    private String tipoBD;
    
    public PasajeroServicio(String tipoBD) {
        this.tipoBD = tipoBD;
        this.fabrica = new FabricaDAOPasajero();
    }
    
    public PasajeroServicio() {
        this("POSTGRE");
    }
    
    public int registrarPasajero(PasajeroDTO pasajero) {
        if (pasajero == null) {
            throw new IllegalArgumentException("El pasajero no puede ser nulo");
        }
        
        if (pasajero.getNombre() == null || pasajero.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        
        if (pasajero.getApellido() == null || pasajero.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        
        
        if (pasajero.getIdentificacion() == null || pasajero.getIdentificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación es obligatoria");
        }
        
        PasajeroDAO dao = fabrica.crearPasajeroDAO(tipoBD);
        return dao.insertarPasajero(pasajero);
    }
    
    public PasajeroDTO consultarPasajero(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }
        
        PasajeroDAO dao = fabrica.crearPasajeroDAO(tipoBD);
        return dao.consultarPasajero(id);
    }
    
    public List<PasajeroDTO> listarPasajeros() {
        PasajeroDAO dao = fabrica.crearPasajeroDAO(tipoBD);
        return dao.listarTodos();
    }
    
    public int actualizarPasajero(PasajeroDTO pasajero) {
        if (pasajero == null || pasajero.getIdPasajero() <= 0) {
            throw new IllegalArgumentException("Datos del pasajero inválidos");
        }
        
        PasajeroDAO dao = fabrica.crearPasajeroDAO(tipoBD);
        PasajeroDTO existente = dao.consultarPasajero(pasajero.getIdPasajero());
        
        if (existente == null) {
            throw new IllegalArgumentException("El pasajero no existe");
        }
        
        if ("POSTGRE".equalsIgnoreCase(tipoBD)) {
            PasajeroDAOPostgre daoPostgre = (PasajeroDAOPostgre) dao;
            return daoPostgre.actualizar(pasajero);
        } else {
            throw new UnsupportedOperationException("Actualización no soportada para " + tipoBD);
        }
    }
    
    public int eliminarPasajero(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }
        
        PasajeroDAO dao = fabrica.crearPasajeroDAO(tipoBD);
        PasajeroDTO existente = dao.consultarPasajero(id);
        
        if (existente == null) {
            throw new IllegalArgumentException("El pasajero no existe");
        }
        
        return dao.borrar(id);
    }
    
    public PasajeroDTO buscarPorIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación es obligatoria");
        }
        
        PasajeroDAO dao = fabrica.crearPasajeroDAO(tipoBD);
        List<PasajeroDTO> todos = dao.listarTodos();
        
        for (PasajeroDTO p : todos) {
            if (p.getIdentificacion().equals(identificacion)) {
                return p;
            }
        }
        return null;
    }
    
    
}
