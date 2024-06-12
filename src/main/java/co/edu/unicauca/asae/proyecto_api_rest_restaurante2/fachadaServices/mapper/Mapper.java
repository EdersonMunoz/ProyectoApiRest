package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
