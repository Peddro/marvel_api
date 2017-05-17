package farfetch.test.marvel.ui.characters.details;

import android.databinding.Bindable;
import android.support.annotation.DrawableRes;
import farfetch.test.marvel.BR;
import farfetch.test.marvel.R;
import farfetch.test.marvel.data.business.models.character.Character;
import farfetch.test.marvel.data.business.models.comic.Comic;
import farfetch.test.marvel.data.business.models.event.Events;
import farfetch.test.marvel.data.business.models.series.Series;
import farfetch.test.marvel.data.business.models.story.Story;
import farfetch.test.marvel.di.activity.ActivityScope;
import farfetch.test.marvel.ui._core.base.BaseViewModel;
import farfetch.test.marvel.ui._models.FavoriteModel;
import farfetch.test.marvel.ui._observers.UiObservers;
import javax.inject.Inject;

/**
 * Created by Pedro Glória on 15/05/2017.
 */

@ActivityScope public class CharacterDetailViewModel extends BaseViewModel<CharacterDetailContract.View> {

  private Character character;

  @Inject CharacterDetailViewModel() {

  }

  public void setCharacter(Character character) {
    this.character = character;
    notifyPropertyChanged(BR.favoriteDrawable);

    initLists();
  }

  public Character getCharacter() {
    return character;
  }

  private void initLists() {
    Comic comics = character.getComics();
    if (comics != null) {
      getView().loadSummary(R.string.comics_lbl, comics.getAvailable(), comics.getComicSummaryList());
    }

    Events events = character.getEvents();
    if (events != null) {
      getView().loadSummary(R.string.events_lbl, events.getAvailable(), events.getEventSummaryList());
    }

    Series series = character.getSeries();
    if (series != null) {
      getView().loadSummary(R.string.series_lbl, series.getAvailable(), series.getSeriesSummaryList());
    }

    Story story = character.getStory();
    if (story != null) {
      getView().loadSummary(R.string.stories_lbl, story.getAvailable(), story.getStoriesSummaryList());
    }
  }

  @Bindable public @DrawableRes int getFavoriteDrawable() {
    return character.isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_not_favorite;
  }

  public void onFavoriteClick() {
    character.setFavorite(!character.isFavorite());
    UiObservers.getFavoriteObservable().onNext(new FavoriteModel(character.isFavorite(), character.getId()));

    notifyPropertyChanged(BR.favoriteDrawable);
  }
}
