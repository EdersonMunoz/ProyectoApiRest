package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipoComidaDTO {

  private int idTipoComida;
  private String nombre;

  public TipoComidaDTO() {}

  @Override
  public String toString() {
    return (
      "TipoComidaDTO [idTipoComida=" + idTipoComida + ", nombre=" + nombre + "]"
    );
  }
}
