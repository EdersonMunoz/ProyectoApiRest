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

  // show: Muestra todas las comidas
  @GetMapping("/comidas/all")
  public List<ComidaDTO> show() {
    System.out.println("show: Muestra todas las comidas");
    return comidaService.findAll();
  }

  // FindByCodigo: Muestra una comida por su codigo
  @GetMapping("/comidas/find/{codigo}")
  public ComidaDTO FindByCodigo(@PathVariable String codigo) {
    System.out.println("Consultando comida con codigo: " + codigo);
    ComidaDTO comidaDto = comidaService.findByCodigo(codigo);
    return comidaDto;
  }

  // Exist: Verifica si una comida existe
  @GetMapping("/comidas/exist/{codigo}")
  public boolean Exist(@PathVariable String codigo) {
    System.out.println(
      "Exist: Consultando si existe la comida con codigo: " + codigo
    );
    return comidaService.findExist(codigo);
  }

  // save: Guarda una comida
  @PostMapping("/comidas/save")
  public ComidaDTO save(@RequestBody ComidaDTO comida) {
    System.out.println(
      "save: Guardando comida con codigo: " + comida.getCodigo()
    );
    ComidaDTO objComidaDTO = null;
    objComidaDTO = comidaService.save(comida);
    return objComidaDTO;
  }

  // update: Actualiza una comida
  @PutMapping("/comidas/update")
  public ComidaDTO update(@RequestBody ComidaDTO comida) {
    String codigo = comida.getCodigo();
    System.out.println("update: Actualizando comida con codigo: " + codigo);
    ComidaDTO objComidaDTO = null;
    ComidaDTO comidaActual = comidaService.update(codigo, comida);
    if (comidaActual != null && comidaService.findExist(codigo)) {
      objComidaDTO = comidaService.update(codigo, comida);
    } else {
      System.out.println("El objeto enviado esta vacio o ya existe");
    }
    return objComidaDTO;
  }

  // delete: Elimina una comida
  @DeleteMapping("/comidas/delete/{codigo}")
  public boolean delete(@PathVariable String codigo) {
    System.out.println("delete: Eliminando comida con codigo: " + codigo);
    boolean band = comidaService.findExist(codigo);
    if (band) {
      band = comidaService.delete(codigo);
    }
    return band;
  }

  // listarCabeceras: Muestra las cabeceras
  @GetMapping("/comidas/listarCabeceras")
  public void listarCabeceras(@RequestHeader Map<String, String> headers) {
    System.out.println("listarCabeceras: Muestra las cabeceras");
    headers.forEach((key, value) -> {
      System.out.println(String.format("Cabecera '%s' = %s", key, value));
    });
  }
}
