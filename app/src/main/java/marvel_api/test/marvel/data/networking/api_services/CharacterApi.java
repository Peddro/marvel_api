package marvel_api.test.marvel.data.networking.api_services;

import marvel_api.test.marvel.data.networking.api_services.responses.CharacterListResponse;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public interface CharacterApi {

  @GET("characters") Single<Response<CharacterListResponse>> fetchCharacters(@Query("offset") int offset);

  @GET("characters") Single<Response<CharacterListResponse>> fetchCharacter(@Query("name") String name);
}
