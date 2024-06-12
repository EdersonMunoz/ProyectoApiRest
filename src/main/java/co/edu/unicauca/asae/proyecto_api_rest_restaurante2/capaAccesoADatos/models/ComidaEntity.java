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
  private float valor;

  public ComidaEntity() {}
}
