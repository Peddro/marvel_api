package farfetch.test.marvel.data.networking;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

class ApiQueryInterceptor implements Interceptor {

  private static final String API_KEY_QUERY = "apikey";
  private static final String API_HASH_QUERY = "hash";
  private static final String API_TIMESTAMP_QUERY = "ts";

  private final String apiKey;
  private final String ts;
  private final String hash;

  ApiQueryInterceptor(String apiKey, String ts, String hash) {
    this.apiKey = apiKey;
    this.ts = ts;
    this.hash = hash;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    HttpUrl url = request.url()
        .newBuilder()
        .addQueryParameter(API_KEY_QUERY, apiKey)
        .addQueryParameter(API_HASH_QUERY, hash)
        .addQueryParameter(API_TIMESTAMP_QUERY, ts)
        .build();
    request = request.newBuilder().url(url).build();
    return chain.proceed(request);
  }
}
