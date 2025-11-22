package DAO;

public class FabricaDAOPasajero extends FabricaDAO {

    @Override
    public PasajeroDAO crearPasajeroDAO(String tipo) {

        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de BD no puede ser nulo");
        }

        switch (tipo.toUpperCase()) {

            case "POSTGRE":
                return new PasajeroDAOPostgre();

            case "MONGO":
                return new PasajeroDAOMongo();

            default:
                throw new IllegalArgumentException(
                    "Tipo de BD no reconocido: " + tipo + 
                    ". Opciones válidas: POSTGRE o MONGO"
                );
        }
    }
}
