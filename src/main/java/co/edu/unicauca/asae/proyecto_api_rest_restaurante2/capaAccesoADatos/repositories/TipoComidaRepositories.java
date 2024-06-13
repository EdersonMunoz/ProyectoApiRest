package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models.TipoComidaEntity;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories.conexion.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Exceptions.DatabaseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class TipoComidaRepositories {

  private final ConexionBD conexionABaseDeDatos;

  public TipoComidaRepositories() {
    conexionABaseDeDatos = new ConexionBD();
  }

  public ArrayList<TipoComidaEntity> findAll() {
    System.out.println("REPOSITORY findAll --> consultar todos los tipoComida");
    ArrayList<TipoComidaEntity> tipoComida = new ArrayList<TipoComidaEntity>();

    conexionABaseDeDatos.conectar();
    try {
      PreparedStatement sentencia = null;
      String consulta = "SELECT * FROM TiposDeComidas";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      ResultSet res = sentencia.executeQuery();
      while (res.next()) {
        TipoComidaEntity objTipoComida = new TipoComidaEntity();
        objTipoComida.setIdTipoComida(res.getInt("ID"));
        objTipoComida.setNombre(res.getString("NOMBRE"));

        tipoComida.add(objTipoComida);
      }
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al consultar todos los tipoComida: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    return tipoComida;
  }
}
