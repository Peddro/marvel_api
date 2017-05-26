package marvel_api.test.marvel.data.business.services;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.Gson;
import marvel_api.test.marvel.data.networking.api_services.responses.ErrorResponse;
import marvel_api.test.marvel.data.networking.exceptions.EmptyResponseException;
import marvel_api.test.marvel.data.networking.exceptions.ErrorException;
import marvel_api.test.marvel.data.networking.exceptions.InternetConnectionException;
import marvel_api.test.marvel.data.networking.exceptions.NotFoundException;
import marvel_api.test.marvel.data.networking.exceptions.ServerException;
import marvel_api.test.marvel.data.networking.exceptions.UncheckedException;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import java.io.IOException;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by Pedro Glória on 14/05/2017.
 * Base class of all the services. The idea here is to have a way to transform the call and the error into
 * something "legible" by the application where we have full control of the error handling
 */
public abstract class BaseService {

  <T> SingleTransformer<Response<T>, T> networkTransform() {
    return upstream -> upstream.onErrorResumeNext(throwable -> {
      if (throwable instanceof HttpException) {
        HttpException httpException = (HttpException) throwable;
        Response response = httpException.response();
        return Single.error(getThrowable(response.message(), response.code(), throwable));
      } else if (throwable instanceof SSLPeerUnverifiedException) {
        return Single.error(new SSLPeerUnverifiedException(throwable.getMessage()));
      } else if (throwable instanceof IOException) {
        return Single.error(new InternetConnectionException(throwable));
      } else if (throwable instanceof NetworkErrorException) {
        return Single.error(new InternetConnectionException(throwable));
      }
      return Single.error(throwable);
    }).flatMap(response -> {
      if (!response.isSuccessful()) {
        ResponseBody responseErrorBody = response.errorBody();
        String error = responseErrorBody.string();

        if (TextUtils.isEmpty(error)) {
          return Single.error(new EmptyResponseException());
        }

        ErrorResponse errorResponse = new Gson().fromJson(error, ErrorResponse.class);
        return Single.error(new ErrorException(errorResponse));
      }

      return Single.just(response.body());
    });
  }

  @NonNull
  private Throwable getThrowable(String message, int code, Throwable throwable) {
    Throwable exception;
    switch (code) {
      case 404:
        exception = new NotFoundException();
        break;
      case 401:
        exception = new UncheckedException(message);
        break;
      case 500:
      case 501:
      case 502:
      case 503:
      case 504:
        exception = new ServerException(throwable);
        break;
      default:
        exception = new UncheckedException(message);
        break;
    }
    return exception;
  }
}
