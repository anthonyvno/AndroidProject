package com.example.anthonyvannoppen.androidproject

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UItest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun memeList_onSelectMeme_showsCorrectMemeInfo(){

        onView(withId(R.id.action_home)).perform(click())

        onView(withId(R.id.fragment_meme_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        onView(withId(R.id.text_detail_titel)).check(matches(withText("Mfw exams")))
        onView(withId(R.id.text_detail_beschrijving)).check(matches(withText("Dit is een test")))

    }

    @Test
    fun select_correct_category(){

        //onView(withId(R.id.action_sort)).perform(click())
        //onData(anything()).atPosition(0).perform(click())

        onView(withId(R.id.action_sort)).perform(click())
        onData(anything()).atPosition(3).perform(click())
        onView(withId(R.id.fragment_meme_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))


        onView(withId(R.id.text_detail_categorie)).check(matches(withText("- Games")))

    }


    @Test
     fun createNewMeme(){
         //maak nieuwe meme
         onView(withId(R.id.action_add)).perform(click())
         onView(withId(R.id.text_add_titel)).perform(typeText("Mfw exams"))
         android.support.test.espresso.Espresso.closeSoftKeyboard()
         onView(withId(R.id.text_add_op)).perform(typeText("test"))
         android.support.test.espresso.Espresso.closeSoftKeyboard()
         onView(withId(R.id.text_add_beschrijving)).perform(typeText("test"))
         android.support.test.espresso.Espresso.closeSoftKeyboard()
         onView(withId(R.id.text_add_afbeelding)).perform(typeText("test"))
         android.support.test.espresso.Espresso.closeSoftKeyboard()
         onView(withId(R.id.button_add_submit)).perform(click())
         //check of deze in de recyclerview zit
         onView(withId(R.id.fragment_meme_list)).check(matches(hasDescendant(withText("Mfw exams"))))



     }

}

