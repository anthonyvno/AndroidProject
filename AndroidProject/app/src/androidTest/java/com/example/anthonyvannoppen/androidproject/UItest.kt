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

    // deze test kijkt wat er in het 1e element van de recyclerview zit: waarden moeten daaraan gelijk zijn
    @Test
    fun memeList_onSelectMeme_showsCorrectMemeInfo() {

        onView(withId(R.id.action_sort)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.fragment_meme_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.text_detail_titel)).check(matches(withText("AA")))
        onView(withId(R.id.text_detail_beschrijving)).check(matches(withText("AA")))

    }

    //deze test kijk of het 1e element van de recyclerview gesorteerd op games een meme categorie games zit
    @Test
    fun select_correct_category() {

        onView(withId(R.id.action_sort)).perform(click())
        onData(anything()).atPosition(3).perform(click())
        onView(withId(R.id.fragment_meme_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))


        onView(withId(R.id.text_detail_categorie)).check(matches(withText("- Games")))

    }


    //maakt een nieuwe meme en kijkt of deze in de recyclerview zit
    @Test
    fun createNewMeme() {
        onView(withId(R.id.action_add)).perform(click())
        onView(withId(R.id.text_add_titel)).perform(typeText("AA"))
        android.support.test.espresso.Espresso.closeSoftKeyboard()
        onView(withId(R.id.text_add_op)).perform(typeText("AA"))
        android.support.test.espresso.Espresso.closeSoftKeyboard()
        onView(withId(R.id.text_add_beschrijving)).perform(typeText("AA"))
        android.support.test.espresso.Espresso.closeSoftKeyboard()
        onView(withId(R.id.text_add_afbeelding)).perform(typeText("AA"))
        android.support.test.espresso.Espresso.closeSoftKeyboard()

        onView(withId(R.id.button_add_submit)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.fragment_meme_list)).check(matches(hasDescendant(withText("AA"))))


    }

    //maakt een nieuwe comment en kijkt of deze in de recyclerview zit
    @Test
    fun postNewComment() {
        onView(withId(R.id.action_sort)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.fragment_meme_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.tekst_detail_commentOp)).perform(typeText("pim"))
        android.support.test.espresso.Espresso.closeSoftKeyboard()
        onView(withId(R.id.tekst_detail_commentTekst)).perform(typeText("pim"))
        android.support.test.espresso.Espresso.closeSoftKeyboard()
        onView(withId(R.id.button_detail_commentSubmit)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.comment_list)).check(matches(hasDescendant(withText("pim"))))


    }

}

