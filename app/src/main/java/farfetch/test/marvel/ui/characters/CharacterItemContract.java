package farfetch.test.marvel.ui.characters;

import farfetch.test.marvel.data.business.models.character.Character;

/**
 * Created by Pedro Glória on 15/05/2017.
 */

public interface CharacterItemContract {

  interface View {

    void launchDetailScreen(Character character);
  }
}
