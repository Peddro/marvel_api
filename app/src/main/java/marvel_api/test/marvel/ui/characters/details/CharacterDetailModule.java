package marvel_api.test.marvel.ui.characters.details;

import android.app.Activity;
import dagger.Binds;
import dagger.Module;

/**
 * Created by Pedro Glória on 15/05/2017.
 */

@Module public abstract class CharacterDetailModule {

  @Binds abstract Activity bindActivity(CharacterDetailActivity activity);
}
