package com.example.mynews;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.mynews.activities.LoginActivity;
import com.example.mynews.activities.RegisterUserActivity;

import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NavigationActivityRegister {

    @Test
    void navigationAcivity(){
        ActivityScenario activityScenario= ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.username_input)).perform(typeText("valeria"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("adaw*"), ViewActions.closeSoftKeyboard());
       onView(withId(R.id.login_error)).check(matches(withText("non_field_errors:No puede iniciar sesión con las credenciales proporcionadas")));
        //onView(withId(R.id.main)).check(matches(isDisplayed()));
    }


}
