package marvel_api.test.marvel.di.activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import marvel_api.test.marvel.ui.characters.details.CharacterDetailActivity;
import marvel_api.test.marvel.ui.characters.details.CharacterDetailModule;
import marvel_api.test.marvel.ui.main.MainActivity;
import marvel_api.test.marvel.ui.main.MainModule;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

@Module public abstract class ActivityBindingModule {

  @ActivityScope @ContributesAndroidInjector(modules = MainModule.class)
  abstract MainActivity mainActivity();

  @ActivityScope @ContributesAndroidInjector(modules = CharacterDetailModule.class)
  abstract CharacterDetailActivity characterDetailActivity();
}
