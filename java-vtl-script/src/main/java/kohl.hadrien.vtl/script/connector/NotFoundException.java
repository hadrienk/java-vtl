package kohl.hadrien.vtl.script.connector;

/**
 * Created by hadrien on 30/09/2016.
 */
public class NotFoundException extends ConnectorException {
  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }
}
