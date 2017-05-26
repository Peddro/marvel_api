package marvel_api.test.marvel.data.business.repositories;

import marvel_api.test.marvel.data.business.models.character.Character;
import marvel_api.test.marvel.data.business.services.CharacterService;
import marvel_api.test.marvel.data.preferences.SharedPrefs;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

@Singleton public class CharacterRepository {

  private final CharacterService characterService;
  private final SharedPrefs sharedPrefs;

  // This should be a Set
  private List<Character> characters;
  private boolean hasLoadedAllData;

  @Inject CharacterRepository(CharacterService characterService, SharedPrefs sharedPrefs) {
    this.characterService = characterService;
    this.sharedPrefs = sharedPrefs;
    this.characters = new ArrayList<>();
    this.hasLoadedAllData = false;
  }

  public Completable fetchCharacters(boolean isPagination) {
    if (shouldShowCachedResults(isPagination)) {
      return Completable.complete();
    }

    int offset = isPagination ? characters.size() : 0;
    return characterService.fetchCharacters(offset)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(requestedCharacters -> {
          addCharacterToList(requestedCharacters);
          //Check if it's the last of the data
          hasLoadedAllData = requestedCharacters.isEmpty();
        })
        .toCompletable();
  }

  public Single<Boolean> fetchCharacter(String characterBeingSearched) {
    return characterService.searchFor(characterBeingSearched)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(requestedCharacters -> {
          if (!requestedCharacters.isEmpty()) {
            // This should be a set to avoid repetitions and we should also sort this
            addCharacterToList(requestedCharacters);
          }
          return Single.just(!requestedCharacters.isEmpty());
        });
  }

  private void addCharacterToList(List<Character> requestedCharacters) {
    Set<String> favorites = sharedPrefs.getFavorites();
    for (Character requestedCharacter : requestedCharacters) {
      requestedCharacter.setFavorite(favorites.contains(requestedCharacter.getId()));
      characters.add(requestedCharacter);
    }
  }

  public List<Character> getCharacterList() {
    return characters;
  }

  private boolean shouldShowCachedResults(boolean isPagination) {
    return !isPagination && !characters.isEmpty() || hasLoadedAllData;
  }
}
