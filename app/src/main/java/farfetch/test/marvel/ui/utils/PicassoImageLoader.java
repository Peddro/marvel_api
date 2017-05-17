package farfetch.test.marvel.ui.utils;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * Created by Pedro Glória on 15/05/2017.
 */

public class PicassoImageLoader implements ImageLoader {

  private final Context context;

  @Inject PicassoImageLoader(Context context) {
    this.context = context;
  }

  @Override public void loadUrl(String url, ImageView imageView) {
    Picasso.with(context).load(url).into(imageView);
  }
}
