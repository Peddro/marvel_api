package marvel_api.test.marvel.ui.characters.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.MenuItem;
import android.widget.ImageView;
import marvel_api.test.marvel.R;
import marvel_api.test.marvel.data.business.models.Thumbnail;
import marvel_api.test.marvel.data.business.models._base.Summary;
import marvel_api.test.marvel.data.business.models.character.Character;
import marvel_api.test.marvel.databinding.ActivityCharacterDetailBinding;
import marvel_api.test.marvel.ui._core.base.BaseActivity;
import marvel_api.test.marvel.ui.utils.ImageLoader;
import marvel_api.test.marvel.ui.widgets.SummaryListView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Pedro Glória on 15/05/2017.
 */

public class CharacterDetailActivity extends BaseActivity<ActivityCharacterDetailBinding, CharacterDetailViewModel>
    implements CharacterDetailContract.View {

  @Inject ImageLoader imageLoader;

  private static final class BundleArgs {
    static final String CHARACTER = "character";
  }

  public static void launchForResult(Activity activity, Character character, ImageView imageView,
      int requestCode) {
    Intent intent = new Intent(activity, CharacterDetailActivity.class);
    intent.putExtra(BundleArgs.CHARACTER, character);
    String coverImageTransitionName = activity.getString(R.string.transition_character_cover);
    ActivityOptionsCompat options =
        ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, coverImageTransitionName);
    ActivityCompat.startActivityForResult(activity, intent, requestCode, options.toBundle());
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getViewModel().initializeWith(this);

    Character character = getIntent().getParcelableExtra(BundleArgs.CHARACTER);
    getViewModel().setCharacter(character);

    //supportPostponeEnterTransition();

    imageLoader.loadUrl(character.getThumbnail().getFullPath(Thumbnail.LANDSCAPE_LARGE),
        binding.characterDetailCoverImageView);

    setSupportActionBar(binding.characterDetailToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override protected int layoutId() {
    return R.layout.activity_character_detail;
  }

  @Override protected void initializeBinding(ActivityCharacterDetailBinding binding) {
    binding.setViewModel(getViewModel());
  }

  @Override public void loadSummary(@StringRes int title, String available, List<Summary> summaries) {
    SummaryListView summaryListView = new SummaryListView(this);
    summaryListView.loadFor(title, Integer.parseInt(available), summaries);
    binding.characterDetailContainerLinearLayout.addView(summaryListView);
  }

  @Override public boolean onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(menuItem);
  }
}
