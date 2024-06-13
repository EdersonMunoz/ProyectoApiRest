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
    System.out.println("SERVICE findAll --> Listando todas las comidas");
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
      "SERVICE findByCodigo --> Consultando comida con codigo: " + codigo
    );
    ComidaEntity comidaEntity =
      this.servicioAccesoABaseDeDatos.findByCodigo(codigo);
    if (comidaEntity != null) {
      ComidaDTO comidaDTO = this.modelMapper.map(comidaEntity, ComidaDTO.class);
      return comidaDTO;
    } else {
      throw new NotFoundException("Comida no encontrada");
    }
  }

  // findExist: Busca si existe una comida por su codigo
  @Override
  public boolean findExist(String codigo) {
    System.out.println(
      "SERVICE findExist --> Consultando si existe la comida con codigo: " +
      codigo
    );
    return this.servicioAccesoABaseDeDatos.findExist(codigo);
  }

  // save: Guarda una comida
  @Override
  public ComidaDTO save(ComidaDTO comida) {
    System.out.println(
      "SERVICE save --> Guardando comida: " + comida.toString()
    );

    ComidaEntity comidaEntity =
      this.modelMapper.map(comida, ComidaEntity.class);

    // // Agrega la comida, si ya existe, la actualiza.
    // ComidaEntity comidaAgregada =
    //   this.servicioAccesoABaseDeDatos.saveOrUpdate(
    //       comidaEntity.getCodigo(),
    //       comidaEntity
    //     );

    ComidaEntity comidaAgregada =
      this.servicioAccesoABaseDeDatos.save(comidaEntity);

    ComidaDTO comidaDTO = this.modelMapper.map(comidaAgregada, ComidaDTO.class);

    return comidaDTO;
  }

  // update: Actualiza una comida
  @Override
  public ComidaDTO update(String codigo, ComidaDTO comida) {
    System.out.println(
      "SERVICE update --> Actualizando comida con codigo: " + comida.getCodigo()
    );

    // Verifica si existe la comida
    if (!this.servicioAccesoABaseDeDatos.findExist(codigo)) {
      throw new NotFoundException("Comida no encontrada");
    }

    ComidaEntity comidaEntity =
      this.modelMapper.map(comida, ComidaEntity.class);
    ComidaEntity comidaEntityActualizada =
      this.servicioAccesoABaseDeDatos.update(codigo, comidaEntity);

    System.out.println(
      "SERVICE update --> Comida actualizada: " +
      comidaEntityActualizada.toString()
    );

    ComidaDTO comidaDTO =
      this.modelMapper.map(comidaEntityActualizada, ComidaDTO.class);
    return comidaDTO;
  }

  @Override
  public boolean delete(String codigo) {
    System.out.println(
      "\n\n\n SERVICE TEST delete --> Eliminando comida con codigo: " +
      codigo +
      "\n\n\n"
    );

    // Verifica si existe la comida
    if (!this.servicioAccesoABaseDeDatos.findExist(codigo)) {
      throw new NotFoundException("Comida no encontrada");
    }

    this.servicioAccesoABaseDeDatos.delete(codigo);
    return true;
  }
}
