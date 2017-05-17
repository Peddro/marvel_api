package farfetch.test.marvel.ui.characters;

import android.databinding.Bindable;
import android.support.annotation.DrawableRes;
import farfetch.test.marvel.BR;
import farfetch.test.marvel.R;
import farfetch.test.marvel.data.business.models.Thumbnail;
import farfetch.test.marvel.data.business.models.character.Character;
import farfetch.test.marvel.ui._core.base.BaseViewModel;
import farfetch.test.marvel.ui._models.FavoriteModel;
import farfetch.test.marvel.ui._observers.UiObservers;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class CharacterItemViewModel extends BaseViewModel {

  private final CharacterItemContract.View view;

  private Character character;

  public CharacterItemViewModel(CharacterItemContract.View view) {
    this.view = view;

    // This logic might be weird but we need to listen to changes made on CharacterDetailActivity in here
    // And this seems the reactive way to do it.... I think.?
    addDisposable(UiObservers.getFavoriteObservable().subscribe(favoriteModel -> {
      if (favoriteModel.getCharacterId().equals(character.getId())) {
        character.setFavorite(!character.isFavorite());
        notifyPropertyChanged(BR.favoriteDrawable);
      }
    }));
  }

  public void bindCharacter(Character character) {
    this.character = character;
  }

  public Character getCharacter() {
    return character;
  }

  public String getThumbnailUrl() {
    return character.getThumbnail().getFullPath(Thumbnail.LANDSCAPE_MEDIUM);
  }

  @Bindable public @DrawableRes int getFavoriteDrawable() {
    return character.isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_not_favorite;
  }

  public void onCardClick() {
    view.launchDetailScreen(character);
  }

  public void onFavoriteClick() {
    UiObservers.getFavoriteObservable().onNext(new FavoriteModel(character.isFavorite(), character.getId()));
  }
}