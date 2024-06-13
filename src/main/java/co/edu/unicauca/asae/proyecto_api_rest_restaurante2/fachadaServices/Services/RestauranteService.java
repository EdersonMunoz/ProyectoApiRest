package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.models.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaAccesoADatos.repositories.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.*;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Exceptions.NotFoundException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService implements IRestauranteService {

  @Autowired
  private RestauranteRepositories servicioAccesoABaseDeDatos;

  @Autowired
  private ModelMapper modelMapper;

  // findAll: Lista todos los restaurante
  @Override
  public List<RestauranteDTO> findAll() {
    System.out.println("SERVICE findAll --> Listando todos los restaurante");
    List<RestauranteEntity> restauranteEntity =
      this.servicioAccesoABaseDeDatos.findAll();
    List<RestauranteDTO> restauranteDTO =
      this.modelMapper.map(
          restauranteEntity,
          new TypeToken<List<RestauranteDTO>>() {}.getType()
        );
    return restauranteDTO;
  }
}
