package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComidaDTO {

  private int idComida;
  private String restaurante;
  private String codigo;
  private String nombre;
  private String tipoComida;
  private int cantidadIngredientes;
  private float precio;

  public ComidaDTO() {}

  @Override
  public String toString() {
    return (
      "ComidaDTO [idComida=" +
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
