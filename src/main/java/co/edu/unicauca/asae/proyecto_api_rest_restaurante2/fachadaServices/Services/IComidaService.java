package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.ComidaDTO;
import java.util.List;

public interface IComidaService {
  public List<ComidaDTO> findAll();

  public ComidaDTO findByCodigo(String codigo);

  public boolean findExist(String codigo);

  public ComidaDTO save(ComidaDTO comida);

  public ComidaDTO update(String codigo, ComidaDTO comida);

  public boolean delete(String codigo);
}
