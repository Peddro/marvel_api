package farfetch.test.marvel.ui.widgets;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import farfetch.test.marvel.R;
import farfetch.test.marvel.data.business.models._base.Summary;
import farfetch.test.marvel.databinding.ViewSummaryListBinding;
import java.util.List;

/**
 * Created by Pedro Glória on 16/05/2017.
 */

public class SummaryListView extends FrameLayout {

  private static final int MAX_SUMMARIES = 3;

  private ViewSummaryListBinding binding;

  public SummaryListView(Context context) {
    super(context);
    init(context);
  }

  private void init(Context context) {
    if (isInEditMode()) {
      LayoutInflater.from(context).inflate(R.layout.view_summary_list, this);
      return;
    }

    binding = ViewSummaryListBinding.inflate(LayoutInflater.from(context), this, true);
  }

  public void loadFor(@StringRes int titleId, int available, List<Summary> summaries) {
    String title = getResources().getString(titleId);
    binding.summaryListTitleTextView.setText(title);

    if (summaries.isEmpty()) {
      String emptyText = String.format(getResources().getString(R.string.summary_empty_text), title);
      binding.summaryListEmptyView.setText(emptyText);
      binding.summaryListEmptyView.setVisibility(VISIBLE);
      return;
    }

    for (int i = 0; i < available && i < MAX_SUMMARIES; i++) {
      String name = summaries.get(i).getName();
      TextView textView = (TextView) LayoutInflater.from(getContext())
          .inflate(R.layout.view_summary_text, binding.summaryListContainerLinearLayout, false);
      textView.setText(name);
      binding.summaryListContainerLinearLayout.addView(textView);
    }
  }
}
