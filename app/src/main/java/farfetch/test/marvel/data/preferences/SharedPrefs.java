package farfetch.test.marvel.data.preferences;

import android.content.Context;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;

/**
 * Created by Pedro Glória on 16/05/2017.
 */

@Singleton public class SharedPrefs extends BasePreferences {

  @Inject SharedPrefs(Context context) {
    super(context);
  }

  @Override String getPreferenceName() {
    return context.getPackageName();
  }

  public void saveFavorites(Set<String> favorites) {
    Timber.i("Saving favorites");
    saveSetPreference(Keys.FAVORITE, favorites);
  }

  public Set<String> getFavorites() {
    return getSetPreference(Keys.FAVORITE);
  }

  private final class Keys {
    private static final String FAVORITE = "favorite";
  }
}
