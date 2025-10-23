package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<>(MainActivity.class);

    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(replaceText(name));
        onView(withId(R.id.button_confirm)).perform(click());
    }
    // Test 1 Check whether the activity correctly switched
    @Test
    public void clickingCity_opensShowActivity() {
        addCity("Edmonton");

        onData(anything()).inAdapterView(withId(R.id.city_list))
                .atPosition(0).perform(click());

        onView(withId(R.id.text_city_name)).check(matches(isDisplayed()));
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }

    // Test 2 Test whether the city name is consistent
    @Test
    public void cityName_isConsistentBetweenListAndShow() {
        String expected = "Vancouver";
        addCity(expected);

        onData(anything()).inAdapterView(withId(R.id.city_list))
                .atPosition(0).perform(click());

        onView(withId(R.id.text_city_name)).check(matches(withText(expected)));
    }

    // Test 3 Test the "back" button
    @Test
    public void backButton_returnsToMainActivity() {
        addCity("Tokyo");

        onData(anything()).inAdapterView(withId(R.id.city_list))
                .atPosition(0).perform(click());

        onView(withId(R.id.btn_back)).perform(click());

        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}