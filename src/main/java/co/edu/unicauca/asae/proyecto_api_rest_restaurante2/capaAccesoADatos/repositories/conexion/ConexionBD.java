package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories.conexion;

import java.sql.*;

/**
 * Esta clase permite que las Clases tipo Entidad tengan persistencia
 * por medio de una base de datos relacional
 */
public class ConexionBD {

  private Connection objConexionBaseDatos;
  private String nombreBaseDatos;
  private String login;
  private String password;
  private String url;

  public ConexionBD() {
    objConexionBaseDatos = null;
    nombreBaseDatos = "comidas";
    login = "root";
    password = "";
    url = "jdbc:mysql://localhost/" + nombreBaseDatos + "?serverTimezone=UTC";
  }

  /**
   * Permite hacer la conexion con la base de datos
   */
  public int conectar() {
    int bandera = -1;
    try {
      Class
        .forName("com.mysql.cj.jdbc.Driver")
        .getDeclaredConstructor()
        .newInstance();
      // crea una instancia de la controlador de la base de datos
      objConexionBaseDatos = DriverManager.getConnection(url, login, password);
      // gnera una conexión con la base de datos
      bandera = 1;
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    return bandera;
  }

  /**
   * Cierra la conexion con la base de datos
   *
   */
  public void desconectar() {
    try {
      objConexionBaseDatos.close();
    } catch (Exception e) {
      System.out.println("Error " + e.getMessage());
    }
  }

  /**
   * Retorna un objeto que almacena la referencia a la conexion con la base de
   * datos
   *
   */
  public Connection getConnection() {
    return objConexionBaseDatos;
  }
}
