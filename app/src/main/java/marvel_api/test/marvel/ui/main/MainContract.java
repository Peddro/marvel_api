package marvel_api.test.marvel.ui.main;

import android.support.annotation.StringRes;
import marvel_api.test.marvel.ui._core.base.BaseContract;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public interface MainContract {

  interface View extends BaseContract.View {

    void toggleKeyboardVisibility(boolean isSearchFieldVisible);

    void notifyAdapter();

    void showDialog(@StringRes int titleId, @StringRes int messageId, String character);

    void dismissKeyboard();

    void updateFilter(String character);
  }
}
