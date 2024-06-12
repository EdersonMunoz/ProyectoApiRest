package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models.ComidaEntity;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories.conexion.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Exceptions.DatabaseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class ComidaRepositories {

  private final ConexionBD conexionABaseDeDatos;

  public ComidaRepositories() {
    conexionABaseDeDatos = new ConexionBD();
  }

  public ArrayList<ComidaEntity> findAll() {
    System.out.println("REPOSITORY findAll --> consultar todas las comidas");
    ArrayList<ComidaEntity> comidas = new ArrayList<ComidaEntity>();

    conexionABaseDeDatos.conectar();
    try {
      PreparedStatement sentencia = null;
      String consulta =
        "SELECT A.IDCOMIDA, R.NOMBRE AS 'RESTAURANTE', A.CODIGO, A.NOMBRE, A.CANTIDADINGREDIENTES, A.PRECIO, A.TIPOCOMIDA FROM ( SELECT C.ID AS 'IDCOMIDA', C.IDRESTAURANTE, C.CODIGO, C.NOMBRE, C.CANTIDADINGREDIENTES, C.PRECIO, T.NOMBRE AS 'TIPOCOMIDA', T.ID AS 'IDTIPOCOMIDA' FROM comidas C INNER JOIN tiposdecomidas T ON C.IDTIPOCOMIDA = T.ID ) A INNER JOIN restaurantes R ON A.IDRESTAURANTE = R.ID";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      ResultSet res = sentencia.executeQuery();
      while (res.next()) {
        ComidaEntity objComida = new ComidaEntity();
        objComida.setIdComida(res.getInt("IDCOMIDA"));
        objComida.setRestaurante(res.getString("RESTAURANTE"));
        objComida.setCodigo(res.getString("CODIGO"));
        objComida.setNombre(res.getString("NOMBRE"));
        objComida.setCantidadIngredientes(res.getInt("CANTIDADINGREDIENTES"));
        objComida.setTipoComida(res.getString("TIPOCOMIDA"));
        objComida.setPrecio(res.getFloat("PRECIO"));
        comidas.add(objComida);
      }
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al consultar todas las comidas: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    return comidas;
  }

  public ComidaEntity save(ComidaEntity objComida) {
    System.out.println(
      "REPOSITORY save --> Guardando comida en la base de datos: " +
      objComida.toString()
    );

    ComidaEntity objComidaAlmacenada = null;
    int resultado = -1;

    try {
      conexionABaseDeDatos.conectar();

      PreparedStatement sentencia = null;

      String consulta =
        "INSERT INTO comidas(comidas.IDRESTAURANTE ,comidas.IDTIPOCOMIDA, comidas.CODIGO, comidas.NOMBRE, comidas.CANTIDADINGREDIENTES, comidas.PRECIO) values(?,?,?,?,?,?)";

      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      sentencia.setString(1, objComida.getRestaurante());
      sentencia.setInt(2, Integer.parseInt(objComida.getTipoComida()));
      sentencia.setString(3, objComida.getCodigo());
      sentencia.setString(4, objComida.getNombre());
      sentencia.setInt(5, objComida.getCantidadIngredientes());
      sentencia.setFloat(6, objComida.getPrecio());
      resultado = sentencia.executeUpdate();
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al guardar la comida: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    if (resultado == 1) {
      objComidaAlmacenada = objComida;
    }
    return objComidaAlmacenada;
  }

  // findByCodigo: Busca una comida por su codigo y retorna un objeto ComidaEntity
  public ComidaEntity findByCodigo(String codigo) {
    System.out.println(
      "REPOSITORY findByCodigo --> Buscando comida por codigo: " + codigo
    );
    ComidaEntity objComida = null;
    conexionABaseDeDatos.conectar();
    try {
      PreparedStatement sentencia = null;
      String consulta =
        "SELECT A.IDCOMIDA, R.NOMBRE AS 'RESTAURANTE', A.CODIGO, A.NOMBRE, A.CANTIDADINGREDIENTES, A.PRECIO, A.TIPOCOMIDA FROM ( SELECT C.ID AS 'IDCOMIDA', C.IDRESTAURANTE, C.CODIGO, C.NOMBRE, C.CANTIDADINGREDIENTES, C.PRECIO, T.NOMBRE AS 'TIPOCOMIDA', T.ID AS 'IDTIPOCOMIDA' FROM comidas C INNER JOIN tiposdecomidas T ON C.IDTIPOCOMIDA = T.ID ) A INNER JOIN restaurantes R ON A.IDRESTAURANTE = R.ID WHERE A.CODIGO = ?";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      sentencia.setString(1, codigo);
      ResultSet res = sentencia.executeQuery();

      while (res.next()) {
        objComida = new ComidaEntity();
        objComida.setIdComida(res.getInt("IDCOMIDA"));
        objComida.setRestaurante(res.getString("RESTAURANTE"));
        objComida.setTipoComida(res.getString("TIPOCOMIDA"));
        objComida.setCodigo(res.getString("CODIGO"));
        objComida.setNombre(res.getString("NOMBRE"));
        objComida.setCantidadIngredientes(res.getInt("CANTIDADINGREDIENTES"));
        objComida.setPrecio(res.getFloat("PRECIO"));
      }
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al consultar la comida por codigo: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    return objComida;
  }

  // findExist: Busca si existe una comida por su codigo y retorna un booleano
  public boolean findExist(String codigo) {
    System.out.println(
      "REPOSITORY findExist --> Buscando si existe la comida con codigo: " +
      codigo
    );
    boolean band = false;

    conexionABaseDeDatos.conectar();
    try {
      PreparedStatement sentencia = null;
      String consulta = "SELECT * FROM comidas WHERE comidas.CODIGO=?";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      sentencia.setString(1, codigo);
      ResultSet res = sentencia.executeQuery();
      while (res.next()) {
        band = true;
      }
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al consultar si existe la comida: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    return band;
  }

  // update: Actualiza una comida y retorna un objeto ComidaEntity
  public ComidaEntity update(String codigo, ComidaEntity objComida) {
    System.out.println(
      "REPOSITORY update --> Actualizando comida con codigo: " + codigo
    );
    ComidaEntity objComidaActualizado = null;

    conexionABaseDeDatos.conectar();

    try {
      String consulta =
        "update comidas set NOMBRE=?, IDRESTAURANTE=?, IDTIPOCOMIDA=?, CANTIDADINGREDIENTES=?, PRECIO=? where CODIGO=?";

      PreparedStatement sentencia = conexionABaseDeDatos
        .getConnection()
        .prepareStatement(consulta);

      sentencia.setString(1, objComida.getNombre());
      sentencia.setString(2, objComida.getRestaurante());
      sentencia.setString(3, objComida.getTipoComida());
      sentencia.setInt(4, objComida.getCantidadIngredientes());
      sentencia.setFloat(5, objComida.getPrecio());
      sentencia.setString(6, codigo);

      int resultado = sentencia.executeUpdate();

      System.out.println(
        "REPOSITORY update --> Resultado de la actualización: " + resultado
      );

      if (resultado == 1) {
        objComidaActualizado = objComida;
      }
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al actualizar la comida: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    System.out.println(
      "REPOSITORY update --> Comida actualizada: " + objComidaActualizado
    );
    return objComidaActualizado;
  }

  // update: Actualiza una comida y retorna un objeto ComidaEntity
  public ComidaEntity updateOld(String codigo, ComidaEntity objComida) {
    System.out.println(
      "REPOSITORY update --> Actualizando comida con codigo: " + codigo
    );
    ComidaEntity objComidaActualizado = null;
    conexionABaseDeDatos.conectar();
    int resultado = -1;
    try {
      String consulta =
        "update comidas set " +
        "comidas.NOMBRE=?," +
        "comidas.IDRESTAURANTE=?," +
        "comidas.IDTIPOCOMIDA=?," +
        "comidas.CANTIDADINGREDIENTES=? ," +
        "comidas.PRECIO=? " +
        "where comidas.IDCOMIDA=?";

      PreparedStatement sentencia = conexionABaseDeDatos
        .getConnection()
        .prepareStatement(consulta);

      sentencia.setString(1, objComida.getNombre());
      sentencia.setInt(2, Integer.parseInt(objComida.getTipoComida()));
      sentencia.setInt(4, objComida.getCantidadIngredientes());
      sentencia.setFloat(5, objComida.getPrecio());
      sentencia.setString(6, codigo);

      resultado = sentencia.executeUpdate();
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al actualizar la comida: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    if (resultado == 1) {
      objComidaActualizado = objComida;
    }
    return objComidaActualizado;
  }

  // delete: Elimina una comida y retorna un booleano, true si se eliminó, false si no
  public boolean delete(String codigo) {
    System.out.println(
      "REPOSITORY delete --> Eliminando comida con codigo: " + codigo
    );

    conexionABaseDeDatos.conectar();
    int resultado = -1;
    try {
      PreparedStatement sentencia = null;
      String consulta = "DELETE FROM comidas WHERE comidas.CODIGO=?";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      sentencia.setString(1, codigo);
      resultado = sentencia.executeUpdate();
      sentencia.close();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al eliminar la comida: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }

    return resultado == 1;
  }

  // saveOrUpdate: Guarda o actualiza una comida y retorna un objeto ComidaEntity
  public ComidaEntity saveOrUpdate(String codigo, ComidaEntity objComida) {
    System.out.println(
      "REPOSITORY saveOrUpdate --> Guardando o actualizando comida"
    );
    ComidaEntity objComidaActualizado = null;
    conexionABaseDeDatos.conectar();
    int resultado = -1;
    try {
      PreparedStatement sentencia = null;
      String consulta =
        "INSERT INTO comidas (CODIGO, NOMBRE, IDRESTAURANTE, IDTIPOCOMIDA, CANTIDADINGREDIENTES, PRECIO) " +
        "VALUES (?, ?, ?, ?, ?, ?) " +
        "ON DUPLICATE KEY UPDATE " +
        "NOMBRE = VALUES(NOMBRE), " +
        "IDRESTAURANTE = VALUES(IDRESTAURANTE), " +
        "IDTIPOCOMIDA = VALUES(IDTIPOCOMIDA), " +
        "CANTIDADINGREDIENTES = VALUES(CANTIDADINGREDIENTES), " +
        "PRECIO = VALUES(PRECIO)";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      sentencia.setString(1, codigo);
      sentencia.setString(2, objComida.getNombre());
      sentencia.setString(3, objComida.getRestaurante());
      sentencia.setString(4, objComida.getTipoComida());
      sentencia.setInt(5, objComida.getCantidadIngredientes());
      sentencia.setDouble(6, objComida.getPrecio());
      resultado = sentencia.executeUpdate();
      if (resultado >= 1) {
        System.out.println(
          "Comida " + codigo + " guardada o actualizada correctamente"
        );
        objComidaActualizado = findByCodigo(codigo);
      } else {
        System.out.println(
          "No se pudo guardar o actualizar la comida " + codigo
        );
      }
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al guardar o actualizar la comida: " + e.getMessage()
      );
    } finally {
      conexionABaseDeDatos.desconectar();
    }
    return objComidaActualizado;
  }
}
