package marvel_api.test.marvel.ui._observers;

import marvel_api.test.marvel.ui._models.FavoriteModel;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Pedro Glória on 16/05/2017.
 */

public class UiObservers {

  private static Subject<FavoriteModel> favoriteObservable;
  private static Subject<Integer> filteredCharactersObservable;

  private static UiObservers instance;

  public static UiObservers getInstance() {
    if (instance == null) {
      instance = new UiObservers();
    }
    return instance;
  }

  private UiObservers() {
  }

  public static Subject<FavoriteModel> getFavoriteObservable() {
    if (favoriteObservable == null) {
      favoriteObservable = PublishSubject.create();
    }
    return favoriteObservable;
  }

  public static Subject<Integer> getFilteredCharactersObservable() {
    if (filteredCharactersObservable == null) {
      filteredCharactersObservable = PublishSubject.create();
    }
    return filteredCharactersObservable;
  }

}
