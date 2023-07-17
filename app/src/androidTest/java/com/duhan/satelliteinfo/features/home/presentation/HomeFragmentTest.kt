import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.duhan.satelliteinfo.MainActivity
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.atPosition
import com.duhan.satelliteinfo.getText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragmentTest {
    @JvmField
    @Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    private val delayMillis = 5000L

    @Test
    fun showHomeFragmentWhenAppStated() {
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun verifyErrorMessageHidden() {
        onView(withId(R.id.error_container))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun verifyLoadingContainerHidden() {
        onView(withId(R.id.loading_container))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun searchSatellites_withValidQuery_shouldDisplayFilteredItems() {
        onView(isAssignableFrom(SearchView::class.java)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("Starship-1"))
        Thread.sleep(delayMillis)
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(0, hasDescendant(withText("Starship-1")))))

    }

    @Test
    fun searchSatellites_withEmptyQuery_shouldDisplayAllItems() {
        onView(isAssignableFrom(SearchView::class.java)).perform(click())
        onView(withId(R.id.search)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText(""))

        Thread.sleep(delayMillis)
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(0, hasDescendant(withText("Starship-1")))))
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(1, hasDescendant(withText("Dragon-1")))))
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(2, hasDescendant(withText("Starship-3")))))

    }

    @Test
    fun show_satellite_detail_when_click_item() {
        onView(withId(R.id.satellites))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<
                        RecyclerView.ViewHolder>(0, click())
            )
        Thread.sleep(delayMillis)
        onView(withId(R.id.detailContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun when_detail_screen_shown_positions_must_change() {
        onView(withId(R.id.satellites))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<
                        RecyclerView.ViewHolder>(0, click())
            )
        Thread.sleep(delayMillis)
        val text = getText(onView(withId(R.id.last_position)))
        Thread.sleep(delayMillis)
        val text2 = getText(onView(withId(R.id.last_position)))


        assert(text != text2)

    }
}

