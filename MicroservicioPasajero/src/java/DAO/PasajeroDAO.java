/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.PasajeroDTO;
import java.util.List;

/**
 *
 * @author amart
 */
public interface PasajeroDAO {
     int insertarPasajero(PasajeroDTO ob);
     PasajeroDTO consultarPasajero(int id);
     List<PasajeroDTO> listarTodos();
     public int borrar(int id);
}
