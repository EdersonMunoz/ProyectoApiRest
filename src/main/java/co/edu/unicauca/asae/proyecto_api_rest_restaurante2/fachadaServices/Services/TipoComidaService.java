package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.*;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoComidaService implements ITipoComidaService {

  @Autowired
  private TipoComidaRepositories servicioAccesoABaseDeDatos;

  @Autowired
  private ModelMapper modelMapper;

  // findAll: Lista todos los restaurante
  @Override
  public List<TipoComidaDTO> findAll() {
    System.out.println("SERVICE findAll --> Listando todos los restaurante");
    List<TipoComidaEntity> restauranteEntity =
      this.servicioAccesoABaseDeDatos.findAll();
    List<TipoComidaDTO> restauranteDTO =
      this.modelMapper.map(
          restauranteEntity,
          new TypeToken<List<TipoComidaDTO>>() {}.getType()
        );
    return restauranteDTO;
  }
}
