package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestauranteDTO {

  private int idRestaurante;
  private int nit;
  private String nombre;

  public RestauranteDTO() {}

  @Override
  public String toString() {
    return (
      "RestauranteDTO [idRestaurante=" +
      idRestaurante +
      ", nit=" +
      nit +
      ", nombre=" +
      nombre +
      "]"
    );
  }
}
