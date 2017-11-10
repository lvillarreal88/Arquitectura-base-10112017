package com.cosmo.arquitecturamvpbase.views.activities;


import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.cosmo.arquitecturamvpbase.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class CreateProductActivityRecordTest {

    @Rule
    public ActivityTestRule<CreateProductActivity> mActivityTestRule = new ActivityTestRule<>(CreateProductActivity.class);

    @Test
    public void createProductActivityRecordTest() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.product_etName),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(TextInputLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.product_etDescription),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(TextInputLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.product_etPrice),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(TextInputLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.product_etQuantity),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(TextInputLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText4.check(matches(withText("")));

        ViewInteraction button = onView(
                allOf(withId(R.id.product_btnCreate),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.product_btnCreate),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.product_etName), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.product_etName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Casa"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.product_etDescription), isDisplayed()));
        appCompatEditText3.perform(replaceText("casa"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.product_etPrice), isDisplayed()));
        appCompatEditText4.perform(replaceText("12000"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.product_etQuantity), isDisplayed()));
        appCompatEditText5.perform(replaceText("5"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.product_btnCreate), withText("Crear"), isDisplayed()));
        appCompatButton.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
