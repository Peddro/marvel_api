package marvel_api.test.marvel.data.networking.exceptions;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class UncheckedException extends Throwable {
  private final String message;

  public UncheckedException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
