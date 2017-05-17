package farfetch.test.marvel.ui._core.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import farfetch.test.marvel.data.business.models.character.Character;
import farfetch.test.marvel.databinding.ItemForCharacterListBinding;
import farfetch.test.marvel.ui._core.view_holders.CharacterItemViewHolder;
import farfetch.test.marvel.ui._observers.UiObservers;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharacterItemViewHolder> implements Filterable {

  private final List<Character> characters;
  private List<Character> filteredCharacters;

  public CharactersAdapter(List<Character> characters) {
    this.characters = characters;
    filteredCharacters = new ArrayList<>(characters);
  }

  @Override public CharacterItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    ItemForCharacterListBinding binding = ItemForCharacterListBinding.inflate(inflater, parent, false);
    return new CharacterItemViewHolder(binding);
  }

  @Override public void onBindViewHolder(CharacterItemViewHolder holder, int position) {
    holder.bind(filteredCharacters.get(position));
  }

  @Override public int getItemCount() {
    return filteredCharacters.size();
  }

  @Override public Filter getFilter() {
    return new Filter() {
      @Override protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        List<Character> filteredCharacters = new ArrayList<>();

        if (constraint == null || constraint.length() == 0) {
          results.count = characters.size();
          results.values = characters;
        } else {
          constraint = constraint.toString().toLowerCase();
          for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            String characterName = character.getName();
            if (characterName.toLowerCase().contains(constraint.toString())) {
              filteredCharacters.add(character);
            }
          }

          results.count = filteredCharacters.size();
          results.values = filteredCharacters;
        }

        UiObservers.getFilteredCharactersObservable().onNext(results.count);
        return results;
      }

      @Override protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        filteredCharacters = (List<Character>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }
}
