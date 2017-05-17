package farfetch.test.marvel.ui._core.view_holders;

import farfetch.test.marvel.data.business.models.character.Character;
import farfetch.test.marvel.databinding.ItemForCharacterListBinding;
import farfetch.test.marvel.ui._core.adapter.BindableViewHolder;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class CharacterItemViewHolder extends BindableViewHolder<ItemForCharacterListBinding, Character> {

  public CharacterItemViewHolder(ItemForCharacterListBinding binding) {
    super(binding);
  }

  @Override public void bind(Character item) {
    binding.rootHeroesItemView.bind(binding, item);
  }
}
