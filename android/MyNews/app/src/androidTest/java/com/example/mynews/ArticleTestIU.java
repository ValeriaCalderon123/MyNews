package com.example.mynews;

import androidx.fragment.app.FragmentActivity;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;

import com.example.mynews.activities.LoginActivity;
import com.example.mynews.fragments.category.CategoryListFragment;
import com.example.mynews.models.Category;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ArticleTestIU {
    @Test
    void userDenied(){
       // FragmentActivity activityScenario= FragmentActivity.launch(LoginActivity.class);
        onView(withId(R.id.username_input)).perform(typeText("valeria"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("adaw*"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_error)).check(matches(withText("non_field_errors:No puede iniciar sesión con las credenciales proporcionadas")));
        //onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
}
