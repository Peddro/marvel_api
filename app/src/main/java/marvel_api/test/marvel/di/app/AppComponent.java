package marvel_api.test.marvel.di.app;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import marvel_api.test.marvel.MarvelApp;
import marvel_api.test.marvel.data.networking.NetworkModule;
import marvel_api.test.marvel.di.activity.ActivityBindingModule;
import marvel_api.test.marvel.ui.characters.CharacterItemView;
import javax.inject.Singleton;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

@Component(modules = {
    AppModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class,
    NetworkModule.class
}) @Singleton public interface AppComponent extends AndroidInjector<MarvelApp> {

  @Component.Builder abstract class Builder extends AndroidInjector.Builder<MarvelApp> {}

  void inject(CharacterItemView characterItemView);
}
