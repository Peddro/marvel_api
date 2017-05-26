package marvel_api.test.marvel.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class MarginItemDecoration extends RecyclerView.ItemDecoration {

  private int space;

  public MarginItemDecoration(int space) {
    this.space = space;
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
      outRect.bottom = space;
      outRect.top = 0;
    }
    if (parent.getChildAdapterPosition(view) == 0) {
      outRect.top = space;
      outRect.bottom = 0;
    }
  }
}
