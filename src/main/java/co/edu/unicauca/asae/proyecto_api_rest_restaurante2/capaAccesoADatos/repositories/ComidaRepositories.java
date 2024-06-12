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
    System.out.println("listando comidas desde bd");
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
        objComida.setValor(res.getFloat("PRECIO"));
        comidas.add(objComida);
      }
      sentencia.close();
      conexionABaseDeDatos.desconectar();
    } catch (SQLException e) {
      System.out.println("error en la inserción: " + e.getMessage());
    }

    return comidas;
  }

  public ComidaEntity save(ComidaEntity objComida) {
    System.out.println("registrando comida");
    ComidaEntity objComidaAlmacenada = null;
    int resultado = -1;

    try {
      conexionABaseDeDatos.conectar();

      PreparedStatement sentencia = null;
      String consulta =
        "INSERT INTO comidas(comidas.IDRESTAURANTE ,comidas.IDTIPOCOMIDA, comidas.CODIGO,comidas.NOMBRE, comidas.CANTIDADINGREDIENTES, comidas.PRECIO) values(?,?,?,?,?,?,?)";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      sentencia.setString(1, objComida.getRestaurante());
      sentencia.setInt(2, Integer.parseInt(objComida.getTipoComida()));
      sentencia.setString(3, objComida.getCodigo());
      sentencia.setString(4, objComida.getNombre());
      sentencia.setInt(5, objComida.getCantidadIngredientes());
      sentencia.setFloat(6, objComida.getValor());
      resultado = sentencia.executeUpdate();
      sentencia.close();
      conexionABaseDeDatos.desconectar();
    } catch (SQLException e) {
      System.out.println("error en la inserción: " + e.getMessage());
    }

    if (resultado == 1) {
      objComidaAlmacenada = objComida;
    }
    return objComidaAlmacenada;
  }

  public ComidaEntity findByCodigo(String codigo) {
    System.out.println("consultar comida");
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

      System.out.println("findByCodigo --> res: " + res);

      while (res.next()) {
        System.out.println("comida encontrada");

        objComida = new ComidaEntity();
        objComida.setIdComida(res.getInt("IDCOMIDA"));
        objComida.setRestaurante(res.getString("RESTAURANTE"));
        objComida.setTipoComida(res.getString("TIPOCOMIDA"));
        objComida.setCodigo(res.getString("CODIGO"));
        objComida.setNombre(res.getString("NOMBRE"));
        objComida.setCantidadIngredientes(res.getInt("CANTIDADINGREDIENTES"));
        objComida.setValor(res.getFloat("PRECIO"));
      }
      sentencia.close();
      conexionABaseDeDatos.desconectar();
    } catch (SQLException e) {
      System.out.println(
        "error en la consulta de una comida : " + e.getMessage()
      );
    }

    return objComida;
  }

  public boolean findExist(String codigo) {
    System.out.println("consultar si exite una comida");
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
        System.out.println("comida encontrada");
        band = true;
      }
      sentencia.close();
      conexionABaseDeDatos.desconectar();
    } catch (SQLException e) {
      System.out.println(
        "error en la consulta de una comida : " + e.getMessage()
      );
    }

    return band;
  }

  public ComidaEntity update(String codigo, ComidaEntity objComida) {
    System.out.println("actualizar comida");
    ComidaEntity objComidaActualizado = null;
    conexionABaseDeDatos.conectar();
    int resultado = -1;
    try {
      PreparedStatement sentencia = null;
      String consulta =
        "update comidas set " +
        "comidas.NOMBRE=?," +
        "comidas.IDRESTAURANTE=?," +
        "comidas.IDTIPOCOMIDA=?," +
        "comidas.CANTIDADINGREDIENTES=? ," +
        "comidas.PRECIO=? " +
        "where comidas.IDCOMIDA=?";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);

      sentencia.setString(1, objComida.getNombre());
      sentencia.setInt(2, Integer.parseInt(objComida.getTipoComida()));
      sentencia.setInt(4, objComida.getCantidadIngredientes());
      sentencia.setFloat(5, objComida.getValor());
      sentencia.setString(6, codigo);

      resultado = sentencia.executeUpdate();
      sentencia.close();
      conexionABaseDeDatos.desconectar();
    } catch (SQLException e) {
      System.out.println("error en la actualización: " + e.getMessage());
    }

    if (resultado == 1) {
      objComidaActualizado = objComida;
    }
    return objComidaActualizado;
  }

  public boolean delete(String codigo) {
    System.out.println("delete --> Eliminando comida con codigo: " + codigo);

    conexionABaseDeDatos.conectar();
    int resultado = -1;
    try {
      PreparedStatement sentencia = null;
      String consulta = "DELETE FROM comidas WHERE comidas.CODIGO=?";
      sentencia =
        conexionABaseDeDatos.getConnection().prepareStatement(consulta);
      sentencia.setString(1, codigo);
      resultado = sentencia.executeUpdate();

      System.out.println("Resultado de la eliminación: " + resultado);

      sentencia.close();
      conexionABaseDeDatos.desconectar();
    } catch (SQLException e) {
      throw new DatabaseException(
        "Error al eliminar la comida: " + e.getMessage()
      );
    }

    return resultado == 1;
  }
}
