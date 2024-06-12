package co.edu.unicauca.asae.proyecto_api_rest_restaurante2.fachadaServices.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DatabaseException extends ResponseStatusException {

  public DatabaseException(String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }
}
