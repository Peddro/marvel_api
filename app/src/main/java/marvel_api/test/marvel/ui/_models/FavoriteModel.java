package marvel_api.test.marvel.ui._models;

/**
 * Created by Pedro Glória on 16/05/2017.
 */

public class FavoriteModel {

  private boolean isAdding;
  private String characterId;

  public FavoriteModel(boolean isAdding, String characterId) {
    this.isAdding = isAdding;
    this.characterId = characterId;
  }

  public boolean isAdding() {
    return isAdding;
  }

  public void setAdding(boolean adding) {
    isAdding = adding;
  }

  public String getCharacterId() {
    return characterId;
  }

  public void setCharacterId(String characterId) {
    this.characterId = characterId;
  }
}
