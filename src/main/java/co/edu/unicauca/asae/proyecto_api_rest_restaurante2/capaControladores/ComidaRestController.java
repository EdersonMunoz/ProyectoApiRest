package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.capaControladores;

import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.DTO.ComidaDTO;
import co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Services.IComidaService;
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
public class ComidaRestController {

  @Autowired
  private IComidaService comidaService;

  // Listar todas las comidas
  @GetMapping("/comidas")
  public List<ComidaDTO> show() {
    System.out.println("listando comidas");
    return comidaService.findAll();
  }

  @GetMapping("/comidas/{codigo}")
  public ComidaDTO FindByCodigo(@PathVariable String codigo) {
    System.out.println("Consultando comida con codigo: " + codigo); // Para ver en consola
    ComidaDTO comidaDto = comidaService.findByCodigo(codigo);
    return comidaDto;
  }

  @GetMapping("/comidas/exist/{codigo}")
  public boolean Exist(@PathVariable String codigo) {
    return comidaService.findExist(codigo);
  }

  @PostMapping("/comidas")
  public ComidaDTO save(@RequestBody ComidaDTO comida) {
    ComidaDTO objComidaDTO = null;
    objComidaDTO = comidaService.save(comida);
    return objComidaDTO;
  }

  @PutMapping("/comidas/{codigo}")
  public ComidaDTO update(
    @PathVariable String codigo,
    @RequestBody ComidaDTO comida
  ) {
    ComidaDTO objComidaDTO = null;
    ComidaDTO comidaActual = comidaService.update(codigo, comida);
    if (comidaActual != null && comidaService.findExist(codigo)) {
      objComidaDTO = comidaService.update(codigo, comida);
    } else {
      System.out.println("El objeto enviado esta vacio o el codigo ya existe");
    }
    return objComidaDTO;
  }

  @DeleteMapping("/comidas/{codigo}")
  public boolean delete(@PathVariable String codigo) {
    System.out.println(
      "Controller delete --> Eliminando comida con codigo: " + codigo
    );
    boolean band = comidaService.findExist(codigo);
    if (band) {
      band = comidaService.delete(codigo);
    }
    return band;
  }

  // listarCabeceras: Muestra las cabeceras
  @GetMapping("/comidas/listarCabeceras")
  public void listarCabeceras(@RequestHeader Map<String, String> headers) {
    System.out.println("cabeceras");
    headers.forEach((key, value) -> {
      System.out.println(String.format("Cabecera '%s' = %s", key, value));
    });
  }
}
