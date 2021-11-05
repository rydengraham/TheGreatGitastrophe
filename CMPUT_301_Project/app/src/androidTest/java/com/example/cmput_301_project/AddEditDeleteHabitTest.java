package com.example.cmput_301_project;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddEditDeleteHabitTest {

    @Rule
    public ActivityTestRule<LoginScreenPage> mActivityTestRule = new ActivityTestRule<>(LoginScreenPage.class);

    @Test
    public void addEditDeleteHabitTest() {
        ViewInteraction materialButton = onView(
allOf(withId(R.id.registerButton), withText("Register"),
childAtPosition(
allOf(withId(R.id.activityNavButtonsHLL),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
1),
isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.createEmailEV),
childAtPosition(
allOf(withId(R.id.createEmailHLL),
childAtPosition(
withId(R.id.pinputPromptsVLL),
0)),
1),
isDisplayed()));
        appCompatEditText.perform(replaceText("habit@mail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.createUsernameEV),
childAtPosition(
allOf(withId(R.id.createUsernameHLL),
childAtPosition(
withId(R.id.pinputPromptsVLL),
1)),
1),
isDisplayed()));
        appCompatEditText2.perform(replaceText("habitUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.createPasswordEV),
childAtPosition(
allOf(withId(R.id.createPasswordHLL),
childAtPosition(
withId(R.id.pinputPromptsVLL),
2)),
1),
isDisplayed()));
        appCompatEditText3.perform(replaceText("habit"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
allOf(withId(R.id.reenterPasswordEV),
childAtPosition(
allOf(withId(R.id.reenterPasswordHLL),
childAtPosition(
withId(R.id.pinputPromptsVLL),
3)),
1),
isDisplayed()));
        appCompatEditText4.perform(replaceText("habit"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
allOf(withId(R.id.signUpButton), withText("Sign Up"),
childAtPosition(
allOf(withId(R.id.fragmentNavButtonsHLL),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
0),
isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
allOf(withId(android.R.id.button2), withText("OK"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
2)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
allOf(withId(R.id.usernameEV),
childAtPosition(
allOf(withId(R.id.enterUsernameHLL),
childAtPosition(
withId(R.id.signinPromptsVLL),
0)),
1),
isDisplayed()));
        appCompatEditText5.perform(replaceText("habitUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
allOf(withId(R.id.passwordEV),
childAtPosition(
allOf(withId(R.id.enterPasswordHLL),
childAtPosition(
withId(R.id.signinPromptsVLL),
1)),
1),
isDisplayed()));
        appCompatEditText6.perform(replaceText("habit"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
allOf(withId(R.id.signInButton), withText("Sign In"),
childAtPosition(
allOf(withId(R.id.activityNavButtonsHLL),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
0),
isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
allOf(withId(R.id.habits), withContentDescription("My Habits"),
childAtPosition(
childAtPosition(
withId(R.id.bottomNavigationView),
0),
1),
isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction button = onView(
allOf(withId(R.id.addButton), withText("ADD A HABIT"),
withParent(allOf(withId(R.id.linearLayout),
withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction materialButton5 = onView(
allOf(withId(R.id.addButton), withText("Add a Habit"),
childAtPosition(
allOf(withId(R.id.linearLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
0),
isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction appCompatEditText7 = onView(
allOf(withId(R.id.habitTitleEditText),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
0)));
        appCompatEditText7.perform(scrollTo(), replaceText("Water Plant"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
allOf(withId(R.id.reasonEditText),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
1)));
        appCompatEditText8.perform(scrollTo(), replaceText("Needs Water"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
allOf(withId(R.id.dateButton), withText("Toggle"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
2),
1)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction materialButton7 = onView(
allOf(withId(R.id.dateButton), withText("Toggle"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
2),
1)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction materialButton8 = onView(
allOf(withId(R.id.frequencyButton), withText("Toggle"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
4),
1)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction materialCheckBox = onView(
allOf(withId(R.id.mondayBox), withText("Monday"),
childAtPosition(
childAtPosition(
withId(R.id.customSelector),
0),
0)));
        materialCheckBox.perform(scrollTo(), click());

        ViewInteraction materialCheckBox2 = onView(
allOf(withId(R.id.tuesdayBox), withText("Tuesday"),
childAtPosition(
childAtPosition(
withId(R.id.customSelector),
0),
1)));
        materialCheckBox2.perform(scrollTo(), click());

        ViewInteraction materialButton9 = onView(
allOf(withId(R.id.frequencyButton), withText("Toggle"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
4),
1)));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction materialButton10 = onView(
allOf(withId(android.R.id.button1), withText("OK"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
3)));
        materialButton10.perform(scrollTo(), click());

        ViewInteraction textView = onView(
allOf(withId(R.id.habitTitle), withText("Water Plant"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
isDisplayed()));
        textView.check(matches(withText("Water Plant")));

        ViewInteraction recyclerView = onView(
allOf(withId(R.id.recyclerView),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView2 = onView(
allOf(withId(R.id.reason), withText("Needs Water"),
withParent(allOf(withId(R.id.expandableLayout),
withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
isDisplayed()));
        textView2.check(matches(withText("Needs Water")));

        ViewInteraction materialButton11 = onView(
allOf(withId(R.id.editButton), withText("Edit"),
childAtPosition(
childAtPosition(
withId(R.id.expandableLayout),
3),
0),
isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction editText = onView(
allOf(withId(R.id.habitTitleEditText), withText("Water Plant"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
isDisplayed()));
        editText.check(matches(withText("Water Plant")));

        ViewInteraction editText2 = onView(
allOf(withId(R.id.reasonEditText), withText("Needs Water"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
isDisplayed()));
        editText2.check(matches(withText("Needs Water")));

        ViewInteraction materialButton12 = onView(
allOf(withId(R.id.frequencyButton), withText("Toggle"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
4),
1)));
        materialButton12.perform(scrollTo(), click());

        ViewInteraction checkBox = onView(
allOf(withId(R.id.mondayBox), withText("Monday"),
withParent(withParent(withId(R.id.customSelector))),
isDisplayed()));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText9 = onView(
allOf(withId(R.id.habitTitleEditText), withText("Water Plant"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
0)));
        appCompatEditText9.perform(scrollTo(), replaceText("Water Plants"));

        ViewInteraction appCompatEditText10 = onView(
allOf(withId(R.id.habitTitleEditText), withText("Water Plants"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
0),
isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
allOf(withId(R.id.reasonEditText), withText("Needs Water"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
1)));
        appCompatEditText11.perform(scrollTo(), replaceText("Plants Needs Water"));

        ViewInteraction appCompatEditText12 = onView(
allOf(withId(R.id.reasonEditText), withText("Plants Needs Water"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
1),
isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
allOf(withId(R.id.reasonEditText), withText("Plants Needs Water"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
1)));
        appCompatEditText13.perform(scrollTo(), click());

        ViewInteraction appCompatEditText14 = onView(
allOf(withId(R.id.reasonEditText), withText("Plants Needs Water"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
1)));
        appCompatEditText14.perform(scrollTo(), replaceText("Plants Need Water"));

        ViewInteraction appCompatEditText15 = onView(
allOf(withId(R.id.reasonEditText), withText("Plants Need Water"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
1),
isDisplayed()));
        appCompatEditText15.perform(closeSoftKeyboard());

        ViewInteraction materialButton13 = onView(
allOf(withId(android.R.id.button1), withText("OK"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
3)));
        materialButton13.perform(scrollTo(), click());

        ViewInteraction textView3 = onView(
allOf(withId(R.id.habitTitle), withText("Water Plants"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
isDisplayed()));
        textView3.check(matches(withText("Water Plants")));

        ViewInteraction textView4 = onView(
allOf(withId(R.id.reason), withText("Plants Need Water"),
withParent(allOf(withId(R.id.expandableLayout),
withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
isDisplayed()));
        textView4.check(matches(withText("Plants Need Water")));

        ViewInteraction materialButton14 = onView(
allOf(withId(R.id.removeButton), withText("Remove a Habit"),
childAtPosition(
allOf(withId(R.id.linearLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
1),
isDisplayed()));
        materialButton14.perform(click());

        ViewInteraction button2 = onView(
allOf(withId(R.id.cancelButton), withText("CANCEL"),
withParent(allOf(withId(R.id.linearLayout),
withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(
allOf(withId(R.id.recyclerView),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction materialButton15 = onView(
allOf(withId(R.id.cancelButton), withText("Cancel"),
childAtPosition(
allOf(withId(R.id.linearLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
3),
isDisplayed()));
        materialButton15.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
allOf(withId(R.id.settings), withContentDescription("More"),
childAtPosition(
childAtPosition(
withId(R.id.bottomNavigationView),
0),
3),
isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction materialButton16 = onView(
allOf(withId(R.id.delete), withText("Delete Account"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
6),
isDisplayed()));
        materialButton16.perform(click());

        ViewInteraction materialButton17 = onView(
allOf(withId(android.R.id.button1), withText("Delete Account"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
3)));
        materialButton17.perform(scrollTo(), click());
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
