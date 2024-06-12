package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComidaEntity {

  private int idComida;
  private String restaurante;
  private String codigo;
  private String nombre;
  private String tipoComida;
  private int cantidadIngredientes;
  private float precio;

  public ComidaEntity() {}

  @Override
  public String toString() {
    return (
      "ComidaEntity [idComida=" +
      idComida +
      ", restaurante=" +
      restaurante +
      ", codigo=" +
      codigo +
      ", nombre=" +
      nombre +
      ", tipoComida=" +
      tipoComida +
      ", cantidadIngredientes=" +
      cantidadIngredientes +
      ", precio=" +
      precio +
      "]"
    );
  }
}
