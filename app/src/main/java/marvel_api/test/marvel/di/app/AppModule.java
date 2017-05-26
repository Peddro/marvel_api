package marvel_api.test.marvel.di.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import marvel_api.test.marvel.MarvelApp;
import marvel_api.test.marvel.data.business.managers.PropertiesManager;
import marvel_api.test.marvel.ui.utils.ImageLoader;
import marvel_api.test.marvel.ui.utils.PicassoImageLoader;
import java.io.InputStream;
import java.util.Properties;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

@Module abstract class AppModule {

  @Binds abstract Application application(MarvelApp marvelApp);

  @Provides @Singleton static Context providesContext(Application application) {
    return application;
  }

  @Provides @Singleton static @Named("env") String provideEnvironment(Application application) {
    try {
      ApplicationInfo ai = application.getPackageManager()
          .getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);
      Bundle bundle = ai.metaData;
      return bundle.getString("environment");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "";
  }

  @Provides @Singleton static Properties provideProperties(@Named("env") String environment) {
    Properties properties = new Properties();
    try {
      InputStream inputStream = PropertiesManager.class.getClassLoader()
          .getResourceAsStream("configurations-" + environment + ".properties");
      properties.load(inputStream);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    return properties;
  }

  @Provides @Singleton static ImageLoader provideImageLoader(PicassoImageLoader picassoImageLoader) {
    return picassoImageLoader;
  }
}
