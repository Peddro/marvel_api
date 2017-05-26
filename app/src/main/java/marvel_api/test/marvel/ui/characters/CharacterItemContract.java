package marvel_api.test.marvel.ui.characters;

import marvel_api.test.marvel.data.business.models.character.Character;

/**
 * Created by Pedro Glória on 15/05/2017.
 */

public interface CharacterItemContract {

  interface View {

    void launchDetailScreen(Character character);
  }
}
