package farfetch.test.marvel;

import android.app.Activity;
import android.app.Application;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import farfetch.test.marvel.di.app.DaggerAppComponent;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

public class MarvelApp extends Application implements HasActivityInjector {

    @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().create(this).inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
