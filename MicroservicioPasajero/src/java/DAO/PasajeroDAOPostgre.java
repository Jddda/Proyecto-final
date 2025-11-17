/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.PasajeroDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author amart
 */
public class PasajeroDAOPostgre implements PasajeroDAO{
     private static final String URL = "jdbc:postgresql://localhost:5432/Prueba";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public PasajeroDAOPostgre() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // INSERTAR
    // ---------------------------------------------------------
     @Override
    public int insertarPasajero(PasajeroDTO p) {
        String sql = "INSERT INTO pasajero (id_pasajero, nombre, apellido, correo, identificacion, telefono) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getIdPasajero());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getCorreo());
            ps.setString(5, p.getIdentificacion());
            ps.setString(6, p.getTelefono());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertando pasajero");
            e.printStackTrace();
        }
        return 0;
    }

    // ---------------------------------------------------------
    // CONSULTAR POR ID
    // ---------------------------------------------------------
     @Override
    public PasajeroDTO consultarPasajero(int id) {
        String sql = "SELECT * FROM pasajero WHERE id_pasajero = ?";
        PasajeroDTO p = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new PasajeroDTO(
                        rs.getInt("id_pasajero"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("identificacion"),
                        rs.getString("telefono")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error consultando pasajero");
            e.printStackTrace();
        }

        return p;
    }

    // ---------------------------------------------------------
    // LISTAR TODOS
    // ---------------------------------------------------------
     @Override
    public List<PasajeroDTO> listarTodos() {
        String sql = "SELECT * FROM pasajero";
        List<PasajeroDTO> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PasajeroDTO p = new PasajeroDTO(
                        rs.getInt("id_pasajero"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("identificacion"),
                        rs.getString("telefono")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error listando pasajeros");
            e.printStackTrace();
        }

        return lista;
    }

    // ---------------------------------------------------------
    // ACTUALIZAR
    // ---------------------------------------------------------
    public int actualizar(PasajeroDTO p) {
        String sql = "UPDATE pasajero SET nombre=?, apellido=?, correo=?, identificacion=?, telefono=? "
                   + "WHERE id_pasajero=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getIdentificacion());
            ps.setString(5, p.getTelefono());
            ps.setInt(6, p.getIdPasajero());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizando pasajero");
            e.printStackTrace();
        }

        return 0;
    }

    // ---------------------------------------------------------
    // BORRAR
    // ---------------------------------------------------------
     @Override
    public int borrar(int id) {
        String sql = "DELETE FROM pasajero WHERE id_pasajero = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error eliminando pasajero");
            e.printStackTrace();
        }

        return 0;
    }

    

}
