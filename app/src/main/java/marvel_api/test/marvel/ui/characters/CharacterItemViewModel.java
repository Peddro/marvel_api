package marvel_api.test.marvel.ui.characters;

import android.databinding.Bindable;
import android.support.annotation.DrawableRes;
import marvel_api.test.marvel.BR;
import marvel_api.test.marvel.R;
import marvel_api.test.marvel.data.business.models.Thumbnail;
import marvel_api.test.marvel.data.business.models.character.Character;
import marvel_api.test.marvel.ui._core.base.BaseViewModel;
import marvel_api.test.marvel.ui._models.FavoriteModel;
import marvel_api.test.marvel.ui._observers.UiObservers;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class CharacterItemViewModel extends BaseViewModel {

  private final CharacterItemContract.View view;

  private Character character;

  public CharacterItemViewModel(CharacterItemContract.View view) {
    this.view = view;
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
    character.setFavorite(!character.isFavorite());
    notifyPropertyChanged(BR.favoriteDrawable);

    UiObservers.getFavoriteObservable().onNext(new FavoriteModel(character.isFavorite(), character.getId()));
  }
}
