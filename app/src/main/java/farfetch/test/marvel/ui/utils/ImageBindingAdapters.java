package farfetch.test.marvel.ui.utils;

import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class ImageBindingAdapters {

  @BindingAdapter("imageResource")
  public static void setImageResource(ImageView imageView, int resource){
    imageView.setImageResource(resource);
  }

  @BindingAdapter({"imageUrl", "placeholder"})
  public static void loadImageWithPlaceholder(ImageView view, String imageUrl, Drawable placeholder) {
    if (!TextUtils.isEmpty(imageUrl)) {
      Picasso picasso = Picasso.with(view.getContext());
      picasso.load(imageUrl).placeholder(placeholder).into(view);
    }
  }

  @BindingAdapter("imageUrl")
  public static void loadImage(ImageView view, String imageUrl) {
    if (!TextUtils.isEmpty(imageUrl)) {
      Picasso picasso = Picasso.with(view.getContext());
      picasso.load(imageUrl).into(view);
    }
  }

  @BindingAdapter("imageTintColor")
  public static void setImageBackgroundColor(ImageView imageView, @ColorRes int color) {
    Drawable drawable = imageView.getDrawable();
    drawable.setColorFilter(ContextCompat.getColor(imageView.getContext(), color), PorterDuff.Mode.SRC_ATOP);
  }
}
