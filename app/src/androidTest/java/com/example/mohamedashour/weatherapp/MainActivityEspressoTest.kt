package com.example.mohamedashour.weatherapp




import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.mohamedashour.weatherapp.ui.activities.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {
    @get:Rule val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun runTestMain() {
        onView(withId(R.id.fl_container)).check(matches(isDisplayed()))
        onView(withText(R.string.app_name)).check(matches(isDisplayed()))
    }
}