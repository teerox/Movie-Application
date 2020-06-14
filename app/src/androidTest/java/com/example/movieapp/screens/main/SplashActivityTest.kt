package com.example.movieapp.screens.main


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.movieapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun splashActivityTest() {
        val appCompatImageView = onView(
            allOf(
                withId(R.id.ImageView2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.ImageView2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        val appCompatImageView3 = onView(
            allOf(
                withId(R.id.ImageView2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView3.perform(click())

        val appCompatImageView4 = onView(
            allOf(
                withId(R.id.ImageView2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView4.perform(click())

        val appCompatImageView5 = onView(
            allOf(
                withId(R.id.ImageView2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView5.perform(click())

        val appCompatImageView6 = onView(
            allOf(
                withId(R.id.ImageView2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView6.perform(click())

        val appCompatImageView7 = onView(
            allOf(
                withId(R.id.search_button),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView7.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editsearchText),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("sonic"), closeSoftKeyboard())

        val appCompatImageView8 = onView(
            allOf(
                withId(R.id.search_button),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView8.perform(click())

        val linearLayout = onView(
            allOf(
                withId(R.id.movie_container),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerId),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        pressBack()

        val linearLayout2 = onView(
            allOf(
                withId(R.id.movie_container),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerId),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout2.perform(click())

        val linearLayout3 = onView(
            allOf(
                withId(R.id.movie_container),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerId),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout3.perform(click())

        val imageView = onView(
            allOf(
                withId(R.id.ImageView2),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
