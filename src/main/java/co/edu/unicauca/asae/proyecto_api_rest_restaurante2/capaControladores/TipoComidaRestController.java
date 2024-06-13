package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaControladores;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.TipoComidaDTO;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services.ITipoComidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TipoComidaRestController {

  @Autowired
  private ITipoComidaService restauranteService;

  // show: Muestra todos los tipoComida
  @GetMapping("/tipoComida/list")
  public List<TipoComidaDTO> show() {
    System.out.println("show: Muestra todos los tipoComida");
    return restauranteService.findAll();
  }
}
