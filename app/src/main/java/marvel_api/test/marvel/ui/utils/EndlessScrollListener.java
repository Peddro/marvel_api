package marvel_api.test.marvel.ui.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
  private int previousTotal = 0;
  private boolean loading = true;

  private LinearLayoutManager linearLayoutManager;

  public EndlessScrollListener(LinearLayoutManager linearLayoutManager) {
    this.linearLayoutManager = linearLayoutManager;
  }

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    if (dy <= 0) {
      // User is not scrolling. Don't load anything
      return;
    }

    int visibleItemCount = recyclerView.getChildCount();
    int totalItemCount = linearLayoutManager.getItemCount();
    int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false;
        previousTotal = totalItemCount;
      }
    }
    int visibleThreshold = 5;
    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
      onLoadMore();
      loading = true;
    }
  }

  public abstract void onLoadMore();
}
