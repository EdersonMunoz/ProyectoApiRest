package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipoComidaEntity {

  private int idTipoComida;
  private String nombre;

  public TipoComidaEntity() {}

  @Override
  public String toString() {
    return (
      "TipoComidaEntity [idTipoComida=" +
      idTipoComida +
      ", nombre=" +
      nombre +
      "]"
    );
  }
}
