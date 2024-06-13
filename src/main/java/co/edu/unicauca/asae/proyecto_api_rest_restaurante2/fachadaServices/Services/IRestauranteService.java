package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.RestauranteDTO;
import java.util.List;

public interface IRestauranteService {
  public List<RestauranteDTO> findAll();
}
