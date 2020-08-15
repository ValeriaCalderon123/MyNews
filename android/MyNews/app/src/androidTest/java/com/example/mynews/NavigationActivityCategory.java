package com.example.mynews;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.mynews.activities.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
public class NavigationActivityCategory {
    @Test
    void navigationAcivity(){
        ActivityScenario activityScenario= ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.button2)).perform();
        onView(withId(R.id.login)).check(matches(isDisplayed()));
    }
}
