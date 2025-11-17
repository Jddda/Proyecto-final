/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PasajeroServicio;

import java.util.List;

/**
 *
 * @author amart
 */
public class PasajeroServicio {
     private PasajeroRepositorio pr;

    public PasajeroServicio() {
        pr = new PasajeroRepositorio();
    }

    public void registrarPasajero(Pasajero pasajero) {
        if (pasajero != null && pasajero.getMetodoPagoPref() != null) {
            pr.savePasajero(pasajero);
        } else {
            throw new IllegalArgumentException("Datos del pasajero inválidos");
        }
    }

    public List<Pasajero> listarPasajeros() {
        return pr.findAllPasajeros();
    }
}
