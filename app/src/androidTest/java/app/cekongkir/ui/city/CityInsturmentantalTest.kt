package app.cekongkir.ui.city

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import app.cekongkir.R
import app.cekongkir.ui.home.HomeActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class CityInsturmentantalTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testChooseCity(){
        onView(withIndex(withId(R.id.edit_origin),0)).perform(click())
        sleep(500)

    }

    fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var currentIndex = 0
            var viewObjHash = 0

            override fun describeTo(description: Description?) {
                description?.appendText(String.format("with index: %d ", index))
                matcher.describeTo(description)
            }


            override fun matchesSafely(item: View?): Boolean {
                if (matcher.matches(item) && currentIndex++ == index) {
                    viewObjHash = item.hashCode()
                }
                return item.hashCode() == viewObjHash
            }
        }
    }
}