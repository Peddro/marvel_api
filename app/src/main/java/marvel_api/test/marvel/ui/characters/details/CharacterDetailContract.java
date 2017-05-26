package marvel_api.test.marvel.ui.characters.details;

import android.support.annotation.StringRes;
import marvel_api.test.marvel.data.business.models._base.Summary;
import marvel_api.test.marvel.ui._core.base.BaseContract;
import java.util.List;

/**
 * Created by Pedro Glória on 16/05/2017.
 */

interface CharacterDetailContract {

  interface View extends BaseContract.View {

    void loadSummary(@StringRes int title, String available, List<Summary> summaries);
  }
}
