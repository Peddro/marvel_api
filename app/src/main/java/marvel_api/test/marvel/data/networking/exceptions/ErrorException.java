package marvel_api.test.marvel.data.networking.exceptions;

import marvel_api.test.marvel.data.networking.api_services.responses.ErrorResponse;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class ErrorException extends Throwable {
  private final ErrorResponse errorResponse;

  public ErrorException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

  public ErrorResponse getErrorResponse() {
    return errorResponse;
  }
}
