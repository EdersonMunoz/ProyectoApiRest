package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaControladores;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.RestauranteDTO;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services.IRestauranteService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestauranteRestController {

  @Autowired
  private IRestauranteService restauranteService;

  // show: Muestra todos los restaurantes
  @GetMapping("/restaurantes/list")
  public List<RestauranteDTO> show() {
    System.out.println("show: Muestra todos los restaurantes");
    return restauranteService.findAll();
  }
}
