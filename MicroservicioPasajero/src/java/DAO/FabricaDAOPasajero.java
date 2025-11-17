/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author amart
 */
public class FabricaDAOPasajero {
    public PasajeroDAO crearResenaDao(String tipo) {
        if(tipo.equals("POSTGRE"))
            return new PasajeroDAOPostgre();
        else
            return new ResenaDAOMongo();
    }
}
