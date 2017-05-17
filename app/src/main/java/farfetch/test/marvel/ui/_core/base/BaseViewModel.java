package farfetch.test.marvel.ui._core.base;

import android.databinding.BaseObservable;
import io.reactivex.CompletableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

public abstract class BaseViewModel<V extends BaseContract.View> extends BaseObservable {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private V view;

  public BaseViewModel() {
  }

  public void initializeWith(V view) {
    this.view = view;
    onCreate();
  }

  void unbind() {
    onDestroy();
    compositeDisposable.dispose();
  }

  public void onCreate() {
    // To override if needed
  }

  public void onDestroy() {
    // To override if needed
  }

  protected V getView() {
    if (view == null) {
      throw new IllegalArgumentException("You must initialize your view callback");
    }
    return view;
  }

  public final void addDisposable(Disposable d) {
    compositeDisposable.add(d);
  }

  protected CompletableTransformer applyNetworkSchedulers() {
    return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}