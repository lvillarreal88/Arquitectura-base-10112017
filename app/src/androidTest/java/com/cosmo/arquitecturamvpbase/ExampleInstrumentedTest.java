package com.cosmo.arquitecturamvpbase;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cosmo.arquitecturamvpbase.views.activities.CreateProductActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {


    @Rule
    public ActivityTestRule<CreateProductActivity> mActivityRule = new ActivityTestRule<>(
            CreateProductActivity.class);


    @Before
    public void initValidString() {
        // Specify a valid string.
    }

    @Test
    public void createProduct() {
        // Type text and then press the button.
        onView(withId(R.id.product_etName))
                .perform(typeText("Empanada"), closeSoftKeyboard());
        onView(withId(R.id.product_etDescription))
                .perform(typeText("Empanada deliciosa"), closeSoftKeyboard());
        onView(withId(R.id.product_etPrice))
                .perform(typeText("1200"), closeSoftKeyboard());
        onView(withId(R.id.product_etQuantity))
                .perform(typeText("13"), closeSoftKeyboard());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.product_btnCreate)).perform(click());
    }


}


