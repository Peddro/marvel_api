package farfetch.test.marvel.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import farfetch.test.marvel.R;
import farfetch.test.marvel.ui._base.BaseActivityTest;
import farfetch.test.marvel.ui.main.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by pedrogloria on 19/05/17 .
 */

@RunWith(AndroidJUnit4.class) public class MainActivityTest extends BaseActivityTest {

    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before public void setUp() throws Exception {
        super.setUp();
    }

    @Test public void ensureSearchAppears() {
        onView(withId(R.id.main_toolbar_search_ImageView)).perform(click());
        onView(withId(R.id.main_search_EditText)).check(matches(isDisplayed()));
    }

    @After public void tearDown() throws Exception {
        super.tearDown();
    }
}
