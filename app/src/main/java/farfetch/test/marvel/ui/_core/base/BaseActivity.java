package farfetch.test.marvel.ui._core.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import farfetch.test.marvel.R;
import javax.inject.Inject;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

public abstract class BaseActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity
    implements BaseContract.View {

  @LayoutRes protected abstract int layoutId();

  protected abstract void initializeBinding(VDB binding);

  @Inject VM viewModel;
  protected VDB binding;

  private ProgressDialog progressDialog;
  protected AlertDialog alertDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setupBinding(DataBindingUtil.setContentView(this, layoutId()));
  }

  @Override protected void onDestroy() {
    dismissDialogs();
    super.onDestroy();
  }

  private void setupBinding(VDB binding) {
    this.binding = binding;
    initializeBinding(binding);
  }

  public final VM getViewModel() {
    return viewModel;
  }

  @Override public void onDetachedFromWindow() {
    viewModel.unbind();
    super.onDetachedFromWindow();
  }

  @Override public void showLoading() {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(this);
    }

    progressDialog.setMessage(getString(R.string.loading_lbl));
    progressDialog.show();
  }

  @Override public void hideLoading() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  protected void showAlertDialog(@StringRes int titleId, String message) {
    alertDialog = new AlertDialog.Builder(this).setTitle(titleId)
        .setMessage(message)
        .setNeutralButton(R.string.ok, null)
        .show();
  }

  private void dismissDialogs() {
    hideLoading();
    hideAlertDialog();
  }

  public void hideAlertDialog() {
    if (alertDialog != null && alertDialog.isShowing()) {
      alertDialog.dismiss();
    }
  }
}
