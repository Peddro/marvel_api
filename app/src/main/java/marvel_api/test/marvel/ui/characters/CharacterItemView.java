package marvel_api.test.marvel.ui.characters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import marvel_api.test.marvel.data.business.models.character.Character;
import marvel_api.test.marvel.databinding.ItemForCharacterListBinding;
import marvel_api.test.marvel.ui._core.base.BaseActivity;
import marvel_api.test.marvel.ui.characters.details.CharacterDetailActivity;
import marvel_api.test.marvel.ui.main.MainActivity;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class CharacterItemView extends FrameLayout implements CharacterItemContract.View {

  private CharacterItemViewModel viewModel;
  private ItemForCharacterListBinding binding;

  public CharacterItemView(@NonNull Context context) {
    super(context);
  }

  public CharacterItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public void bind(ItemForCharacterListBinding binding, Character character) {
    this.binding = binding;
    // This should be done with Coordinators and dagger: https://hackernoon
    // .com/coordinators-solving-a-problem-you-didnt-even-know-you-had-e86623f15ebf?gi=5b9a6314acf6
    // Will attempt that in my next project
    this.viewModel = new CharacterItemViewModel(this);

    this.binding.setViewModel(viewModel);
    this.viewModel.bindCharacter(character);
  }

  @Override public void launchDetailScreen(Character character) {
    CharacterDetailActivity.launchForResult(getActivity(), character, binding.characterItemCoverImageView,
        MainActivity.DETAIL_REQUEST_CODE);
  }

  private Activity getActivity() {
    return (BaseActivity) getContext();
  }
}
