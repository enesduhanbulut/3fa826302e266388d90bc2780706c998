import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.duhan.satelliteinfo.MainActivity
import com.duhan.satelliteinfo.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragmentTest {
    @JvmField
    @Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun showHomeFragmentWhenAppStated() {
        Espresso.onView(ViewMatchers.withId(R.id.satellites))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun verifyErrorMessageHidden() {
        Espresso.onView(ViewMatchers.withId(R.id.error_container))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun verifyLoadingContainerHidden() {
        Espresso.onView(ViewMatchers.withId(R.id.loading_container))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }
}