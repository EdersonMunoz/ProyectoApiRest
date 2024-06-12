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
public class ComidaService implements IComidaService {

  @Autowired
  private ComidaRepositories servicioAccesoABaseDeDatos;

  @Autowired
  private ModelMapper modelMapper;

  // findAll: Lista todas las comidas
  @Override
  public List<ComidaDTO> findAll() {
    List<ComidaEntity> comidasEntity =
      this.servicioAccesoABaseDeDatos.findAll();
    List<ComidaDTO> comidasDTO =
      this.modelMapper.map(
          comidasEntity,
          new TypeToken<List<ComidaDTO>>() {}.getType()
        );
    return comidasDTO;
  }

  // findByCodigo: Busca una comida por su codigo
  @Override
  public ComidaDTO findByCodigo(String codigo) {
    System.out.println(
      "findByCodigo --> Consultando comida con codigo: " + codigo
    );
    ComidaEntity comidaEntity =
      this.servicioAccesoABaseDeDatos.findByCodigo(codigo);
    System.out.println("findByCodigo --> comidaEntity: " + comidaEntity);
    if (comidaEntity != null) {
      ComidaDTO comidaDTO = this.modelMapper.map(comidaEntity, ComidaDTO.class);
      System.out.println("comidaDTO: " + comidaDTO);
      return comidaDTO;
    } else {
      throw new NotFoundException("Comida no encontrada");
    }
  }

  // findExist: Busca si existe una comida por su codigo
  @Override
  public boolean findExist(String codigo) {
    return this.servicioAccesoABaseDeDatos.findExist(codigo);
  }

  // save: Guarda una comida
  @Override
  public ComidaDTO save(ComidaDTO comida) {
    ComidaEntity comidaEntity =
      this.modelMapper.map(comida, ComidaEntity.class);
    ComidaEntity comidaEntityActualizada =
      this.servicioAccesoABaseDeDatos.save(comidaEntity);
    ComidaDTO comidaDTO =
      this.modelMapper.map(comidaEntityActualizada, ComidaDTO.class);
    return comidaDTO;
  }

  // update: Actualiza una comida
  @Override
  public ComidaDTO update(String codigo, ComidaDTO comida) {
    ComidaEntity comidaEntity =
      this.modelMapper.map(comida, ComidaEntity.class);
    ComidaEntity comidaEntityActualizado =
      this.servicioAccesoABaseDeDatos.update(codigo, comidaEntity);
    ComidaDTO comidaDTO =
      this.modelMapper.map(comidaEntityActualizado, ComidaDTO.class);
    return comidaDTO;
  }

  // delete: Elimina una comida
  @Override
  public boolean delete(String codigo) {
    System.out.println("delete --> Eliminando comida con codigo: " + codigo);

    // Verifica si existe la comida
    if (!this.servicioAccesoABaseDeDatos.findExist(codigo)) {
      throw new NotFoundException("Comida no encontrada");
    }

    // Elimina la comida
    return this.servicioAccesoABaseDeDatos.delete(codigo);
  }
}
