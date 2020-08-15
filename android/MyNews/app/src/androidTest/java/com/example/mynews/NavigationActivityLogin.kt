package com.example.mynews


import androidx.core.content.MimeTypeFilter.matchesMany
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mynews.activities.LoginActivity
import com.example.mynews.activities.RegisterUserActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationActivityLogin {
    @Test
    fun test_navLogintoRegister(){
        val activityScenario = ActivityScenario.launch(RegisterUserActivity::class.java)
        onView(withId(R.id.button2)).perform()
        onView(withId(R.id.login)).check(matches(isDisplayed()))
    }
}