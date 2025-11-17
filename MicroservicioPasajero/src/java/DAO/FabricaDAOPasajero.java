/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author amart
 */
public class FabricaDAOPasajero extends FabricaDAO {

    @Override
    public PasajeroDAO crearPasajeroDAO(String tipo) {

        if ("POSTGRE".equalsIgnoreCase(tipo)) {
            return new PasajeroDAOPostgre();
        } else {
            return new PasajeroDAOMongo();
        }

        
    }
}
