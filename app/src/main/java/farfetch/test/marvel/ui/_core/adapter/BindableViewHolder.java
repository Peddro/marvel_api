package farfetch.test.marvel.ui._core.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public abstract class BindableViewHolder<B extends ViewDataBinding, T> extends RecyclerView.ViewHolder {

  protected final B binding;

  protected T item;

  public BindableViewHolder(B binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public abstract void bind(T item);

  public T getItem() {
    return item;
  }
}
