package farfetch.test.marvel.data.business.services;

import farfetch.test.marvel.data.business.models.character.Character;
import farfetch.test.marvel.data.networking.api_services.CharacterApi;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

@Singleton public class CharacterService extends BaseService {

  private final CharacterApi characterApi;

  @Inject CharacterService(CharacterApi characterApi) {
    this.characterApi = characterApi;
  }

  public Single<List<Character>> fetchCharacters(int page) {
    return characterApi.fetchCharacters(page)
        .compose(networkTransform())
        .map(response -> response.getData().getList());
  }

  public Single<List<Character>> searchFor(String characterName) {
    return characterApi.fetchCharacter(characterName)
        .compose(networkTransform())
        .map(response -> response.getData().getList());
  }
}
