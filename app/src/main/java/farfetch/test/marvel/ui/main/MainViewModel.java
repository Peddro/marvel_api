package farfetch.test.marvel.ui.main;

import android.databinding.Bindable;
import android.view.View;
import farfetch.test.marvel.BR;
import farfetch.test.marvel.R;
import farfetch.test.marvel.data.business.models.character.Character;
import farfetch.test.marvel.data.business.repositories.CharacterRepository;
import farfetch.test.marvel.data.networking.utils.ApiErrorResolver;
import farfetch.test.marvel.data.preferences.SharedPrefs;
import farfetch.test.marvel.ui._core.base.BaseViewModel;
import farfetch.test.marvel.ui._observers.UiObservers;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

public class MainViewModel extends BaseViewModel<MainContract.View> {

  private final CharacterRepository characterRepository;
  private final SharedPrefs sharedPrefs;

  private Set<String> favorites;
  private boolean isSearchFieldVisible;
  private boolean isEmptySearchVisible;
  private String characterBeingSearched;

  @Inject MainViewModel(CharacterRepository characterRepository, SharedPrefs sharedPrefs) {
    this.characterRepository = characterRepository;
    this.sharedPrefs = sharedPrefs;
    this.isSearchFieldVisible = false;
    this.isEmptySearchVisible = false;
  }

  @Override public void onCreate() {
    // Get the first page
    subscribeForCharacters(false);
    subscribeForFavorites();
    subscribeForFilters();
  }

  void onStart() {
    favorites = sharedPrefs.getFavorites();
  }

  void onStop() {
    saveFavorites();
  }

  List<Character> getCharacterList() {
    return characterRepository.getCharacterList();
  }

  void loadMore() {
    subscribeForCharacters(true);
  }

  void saveFavorites() {
    sharedPrefs.saveFavorites(favorites);
  }

  @Bindable public int getTitleVisibility() {
    return isSearchFieldVisible ? View.GONE : View.VISIBLE;
  }

  @Bindable public int getSearchFieldVisibility() {
    return isSearchFieldVisible ? View.VISIBLE : View.GONE;
  }

  @Bindable public int getSearchDrawable() {
    return isSearchFieldVisible ? R.drawable.ic_close : R.drawable.ic_search;
  }

  @Bindable public int getEmptySearchVisibility() {
    return isEmptySearchVisible ? View.VISIBLE : View.GONE;
  }

  @Bindable public int getRecyclerVisibility() {
    return isEmptySearchVisible ? View.GONE : View.VISIBLE;
  }

  public void onSearchClick() {
    isSearchFieldVisible = !isSearchFieldVisible;
    getView().toggleKeyboardVisibility(isSearchFieldVisible);

    notifyPropertyChanged(BR.searchFieldVisibility);
    notifyPropertyChanged(BR.searchDrawable);
    notifyPropertyChanged(BR.titleVisibility);
  }

  void addSearchText(String characterBeingSearched) {
    this.characterBeingSearched = characterBeingSearched;
  }

  private void toggleEmptyState(boolean enabled) {
    isEmptySearchVisible = enabled;
    notifyPropertyChanged(BR.emptySearchVisibility);
    notifyPropertyChanged(BR.recyclerVisibility);
  }

  public void onGetCharacterInfo() {
    getView().dismissKeyboard();
    characterRepository.fetchCharacter(characterBeingSearched)
        .doOnSubscribe(ignored -> getView().showLoading())
        .doFinally(() -> getView().hideLoading())
        .subscribe(new SingleObserver<Boolean>() {
          @Override public void onSubscribe(Disposable d) {
            addDisposable(d);
          }

          @Override public void onSuccess(Boolean aBoolean) {
            if (aBoolean) {
              toggleEmptyState(false);
              getView().updateFilter(characterBeingSearched);
            } else {
              getView().showDialog(R.string.not_found, R.string.character_not_found, characterBeingSearched);
            }
          }

          @Override public void onError(Throwable e) {
            ApiErrorResolver.resolve(e);
          }
        });
  }

  // This is not really a 100% reactive way to do pagination...
  // We probably should "play" with Rx operations concatWith and concatMap but I would need more time to figure
  // out how to do that effectively.
  private void subscribeForCharacters(boolean pagination) {
    characterRepository.fetchCharacters(pagination)
        .doOnSubscribe(ignored -> getView().showLoading())
        .doFinally(() -> getView().hideLoading())
        .subscribe(new CompletableObserver() {
          @Override public void onSubscribe(Disposable d) {
            addDisposable(d);
          }

          @Override public void onComplete() {
            getView().notifyAdapter();
          }

          @Override public void onError(Throwable e) {
            ApiErrorResolver.resolve(e);
          }
        });
  }

  private void subscribeForFavorites() {
    addDisposable(UiObservers.getFavoriteObservable().observeOn(Schedulers.io()).subscribe(favoriteModel -> {
      if (favoriteModel.isAdding()) {
        Timber.i("Adding: %s", favoriteModel.getCharacterId());
        favorites.add(favoriteModel.getCharacterId());
      } else {
        Timber.i("Removing: %s", favoriteModel.getCharacterId());
        favorites.remove(favoriteModel.getCharacterId());
      }
    }));
  }

  private void subscribeForFilters() {
    addDisposable(
        UiObservers.getFilteredCharactersObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(size -> {
          toggleEmptyState(size == 0);
        }));
  }
}
