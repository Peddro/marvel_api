package farfetch.test.marvel.ui.main;

import android.app.Activity;
import dagger.Binds;
import dagger.Module;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

@Module public abstract class MainModule {

  @Binds abstract Activity bindActivity(MainActivity activity);
}
