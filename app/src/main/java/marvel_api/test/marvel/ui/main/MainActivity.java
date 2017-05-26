package marvel_api.test.marvel.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.jakewharton.rxbinding2.widget.RxTextView;
import marvel_api.test.marvel.R;
import marvel_api.test.marvel.databinding.ActivityMainBinding;
import marvel_api.test.marvel.ui.MarginItemDecoration;
import marvel_api.test.marvel.ui._core.adapter.CharactersAdapter;
import marvel_api.test.marvel.ui._core.base.BaseActivity;
import marvel_api.test.marvel.ui.utils.EndlessScrollListener;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainContract.View {

  public static final int DETAIL_REQUEST_CODE = 1;

  private CharactersAdapter adapter;

  //<editor-fold desc="Activity lifecycle">
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolbar();
  }

  @Override protected void onStart() {
    super.onStart();
    getViewModel().onStart();
  }

  @Override protected void onStop() {
    getViewModel().onStop();
    super.onStop();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == DETAIL_REQUEST_CODE) {
      getViewModel().saveFavorites();
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  //</editor-fold>

  @Override protected int layoutId() {
    return R.layout.activity_main;
  }

  @Override protected void initializeBinding(ActivityMainBinding binding) {
    binding.setViewModel(getViewModel());
    setupList();
    getViewModel().initializeWith(this);
  }

  private void initToolbar() {
    setSupportActionBar(binding.mainToolbar);
  }

  private void setupList() {
    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    binding.mainRecyclerView.setLayoutManager(linearLayoutManager);

    adapter = new CharactersAdapter(getViewModel().getCharacterList());
    binding.mainRecyclerView.setAdapter(adapter);

    int margin = getResources().getDimensionPixelSize(R.dimen.margin_default);
    binding.mainRecyclerView.addItemDecoration(new MarginItemDecoration(margin));

    binding.mainRecyclerView.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
      @Override public void onLoadMore() {
        getViewModel().loadMore();
      }
    });

    RxTextView.afterTextChangeEvents(binding.mainSearchEditText).subscribe(event -> {
      getViewModel().addSearchText(event.view().getText().toString());
      adapter.getFilter().filter(event.editable());
    });
  }

  @Override public void updateFilter(String character) {
    adapter.getFilter().filter(character);
  }

  @Override public void notifyAdapter() {
    adapter.notifyDataSetChanged();
  }

  @Override public void showDialog(@StringRes int titleId, @StringRes int messageId, String character) {
    String message = String.format(getString(R.string.character_not_found), character);
    showAlertDialog(titleId, message);
  }

  @Override public void toggleKeyboardVisibility(boolean isSearchFieldVisible) {
    if (isSearchFieldVisible) {
      // We need to add a little delay to show the keyboard or it won't work
      new Handler().postDelayed(() -> showKeyboard(binding.mainSearchEditText), 100);
    } else {
      dismissKeyboard(binding.mainSearchEditText);
    }
  }

  @Override public void dismissKeyboard() {
    dismissKeyboard(getCurrentFocus());
  }

  public void dismissKeyboard(View currentFocus) {
    if (currentFocus != null) {
      InputMethodManager imm =
          (InputMethodManager) currentFocus.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }
  }

  public static void showKeyboard(View focus) {
    if (focus != null) {
      focus.requestFocusFromTouch();
      InputMethodManager mgr =
          (InputMethodManager) focus.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      mgr.showSoftInput(focus, InputMethodManager.SHOW_IMPLICIT);
    }
  }
}
