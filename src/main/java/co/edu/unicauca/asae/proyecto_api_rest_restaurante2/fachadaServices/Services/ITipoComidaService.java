package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.TipoComidaDTO;
import java.util.List;

public interface ITipoComidaService {
  public List<TipoComidaDTO> findAll();
}
