package com.example.mytestapp

import android.app.Application
import android.os.Build
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(
    sdk = [Build.VERSION_CODES.VANILLA_ICE_CREAM],
    // minSdk = Build.VERSION_CODES.KITKAT,
    // maxSdk = Build.VERSION_CODES.TIRAMISU,
    application = Application::class
)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val context = getApplicationContext<Application>()

    @Test
    fun `show factorial result in text view`() {
        val scenario = launch(MainActivity::class.java)
        // val scenario =launchFragmentInContainer<MainFragment>()

        scenario.moveToState(Lifecycle.State.RESUMED)
        // scenario.moveToState(Lifecycle.State.CREATED)

        scenario.onActivity { activity ->
            /*activity.findViewById<EditText>(R.id.edit_text).setText(5.toString())
            activity.findViewById<Button>(R.id.button).performClick()
            assertEquals(
                context.getString(R.string.result, "120"),
                activity.findViewById<TextView>(R.id.text_view).text
            )*/

            onView(withId(R.id.edit_text)).perform(typeText("5"))
            onView(withId(R.id.button)).perform(click())
            onView(withId(R.id.text_view)).check(
                matches(withText(activity.getString(R.string.result, "120")))
            )
        }
    }

    @Test
    fun `show sum result in text view`() {
        val scenario = launch(MainActivity::class.java)

        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->
            onView(withId(R.id.edit_text)).perform(replaceText("5"))
            onView(withId(R.id.button)).perform(click())
            onView(withId(R.id.text_view)).check(
                matches(
                    withText(
                        activity.getString(
                            R.string.the_sum_of_numbers_from_1_to_is,
                            5,
                            "15"
                        )
                    )
                )
            )
        }
    }
}