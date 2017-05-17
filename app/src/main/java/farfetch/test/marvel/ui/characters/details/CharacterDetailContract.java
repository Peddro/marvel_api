package farfetch.test.marvel.ui.characters.details;

import android.support.annotation.StringRes;
import farfetch.test.marvel.data.business.models._base.Summary;
import farfetch.test.marvel.ui._core.base.BaseContract;
import java.util.List;

/**
 * Created by Pedro Glória on 16/05/2017.
 */

interface CharacterDetailContract {

  interface View extends BaseContract.View {

    void loadSummary(@StringRes int title, String available, List<Summary> summaries);
  }
}
