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
  private float valor;

  public ComidaDTO() {}
}
