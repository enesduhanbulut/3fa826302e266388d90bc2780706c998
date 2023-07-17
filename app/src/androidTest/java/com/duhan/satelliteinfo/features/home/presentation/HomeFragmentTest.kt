import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
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
        onView(isAssignableFrom(SearchView::class.java)).perform(ViewActions.click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("Starship-1"))
        Thread.sleep(delayMillis)
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(0, hasDescendant(withText("Starship-1")))))

    }

    @Test
    fun searchSatellites_withEmptyQuery_shouldDisplayAllItems() {
        onView(isAssignableFrom(SearchView::class.java)).perform(ViewActions.click())
        onView(withId(R.id.search)).perform(ViewActions.click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText(""))

        Thread.sleep(delayMillis)
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(0, hasDescendant(withText("Starship-1")))))
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(1, hasDescendant(withText("Dragon-1")))))
        onView(withId(R.id.satellites))
            .check(ViewAssertions.matches(atPosition(2, hasDescendant(withText("Starship-3")))))

    }
}

