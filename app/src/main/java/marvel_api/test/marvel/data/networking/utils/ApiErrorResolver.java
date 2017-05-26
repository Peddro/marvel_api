package marvel_api.test.marvel.data.networking.utils;

import marvel_api.test.marvel.data.networking.exceptions.ErrorException;
import timber.log.Timber;

/**
 * Created by Pedro Glória on 14/05/2017.
 * The goal of this class is to resolve the errors into something that the user can read.
 */
public class ApiErrorResolver {

  public static String resolve(Throwable t) {
    Timber.e(t);

    if (t instanceof ErrorException) {
      String errorMessage = ((ErrorException) t).getErrorResponse().getMessage();
      Timber.e(errorMessage);
      return errorMessage;
    }

    return t.getMessage();
  }
}
