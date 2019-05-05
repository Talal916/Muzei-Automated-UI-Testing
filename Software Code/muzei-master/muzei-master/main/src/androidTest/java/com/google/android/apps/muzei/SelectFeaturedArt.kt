package com.google.android.apps.muzei


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import net.nurik.roman.muzei.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SelectFeaturedArt {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MuzeiActivity::class.java)

    @Test
    fun selectFeaturedArt() {
        val button = onView(
                allOf(withId(R.id.activate_muzei_button),
                        childAtPosition(
                                allOf(withId(R.id.intro_container),
                                        childAtPosition(
                                                withId(R.id.container),
                                                0)),
                                3),
                        isDisplayed()))
        button.check(matches(isDisplayed()))

        val button2 = onView(
                allOf(withId(R.id.activate_muzei_button),
                        childAtPosition(
                                allOf(withId(R.id.intro_container),
                                        childAtPosition(
                                                withId(R.id.container),
                                                0)),
                                3),
                        isDisplayed()))
        button2.check(matches(isDisplayed()))

        val frameLayout = onView(
                allOf(withId(R.id.main_choose_provider), withContentDescription("Sources"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()))
        frameLayout.check(matches(isDisplayed()))

        val viewGroup = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.provider_list),
                                0),
                        0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
