package kohl.hadrien.vtl.script.connector;

/**
 * Base exception class for {@link Connector}s.
 */
public class ConnectorException extends Exception {
  public ConnectorException(String message) {
    super(message);
  }

  public ConnectorException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConnectorException(Throwable cause) {
    super(cause);
  }
}
