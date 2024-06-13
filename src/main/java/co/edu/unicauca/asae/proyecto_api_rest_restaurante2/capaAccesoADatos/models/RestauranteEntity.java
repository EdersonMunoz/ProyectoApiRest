package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestauranteEntity {

  private int idRestaurante;
  private int nit;
  private String nombre;

  public RestauranteEntity() {}

  @Override
  public String toString() {
    return (
      "RestauranteEntity [idRestaurante=" +
      idRestaurante +
      ", nit=" +
      nit +
      ", nombre=" +
      nombre +
      "]"
    );
  }
}
