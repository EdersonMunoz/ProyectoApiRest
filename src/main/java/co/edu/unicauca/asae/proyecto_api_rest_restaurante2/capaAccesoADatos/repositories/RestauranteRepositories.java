package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models.RestauranteEntity;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories.conexion.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Exceptions.DatabaseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class RestauranteRepositories {

  private final ConexionBD conexionABaseDeDatos;

  public RestauranteRepositories() {
    conexionABaseDeDatos = new ConexionBD();
  }

  public ArrayList<RestauranteEntity> findAll() {
    System.out.println(
      "REPOSITORY findAll --> consultar todos los restaurantes"
    );
    ArrayList<RestauranteEntity> restaurantes = new ArrayList<RestauranteEntity>();

    conexionABaseDeDatos.conectar();
    try {
      PreparedStatement sentencia = null;
      String consulta = "SELECT * FROM RESTAURANTES";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      ResultSet res = sentencia.executeQuery();
      while (res.next()) {
        RestauranteEntity objRestaurante = new RestauranteEntity();
        objRestaurante.setIdRestaurante(res.getInt("ID"));
        objRestaurante.setNit(res.getInt("NIT"));
        objRestaurante.setNombre(res.getString("NOMBRE"));

        restaurantes.add(objRestaurante);
      }
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al consultar todos los restaurantes: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    return restaurantes;
  }
}
