package com.example.cmput_301_project;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.cmput_301_project.pages.LoginScreenPage;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditUsernameTest {

    @Rule
    public ActivityTestRule<LoginScreenPage> mActivityTestRule = new ActivityTestRule<>(LoginScreenPage.class);

    @Test
    public void editUsernameTest() {
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
        appCompatEditText.perform(replaceText("editUser@mail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.createUsernameEV),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("editUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.createPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.createPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("edit"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.reenterPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.reenterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("edit"), closeSoftKeyboard());

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
        appCompatEditText5.perform(replaceText("editUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.passwordEV),
                        childAtPosition(
                                allOf(withId(R.id.enterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("edit"), closeSoftKeyboard());

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
                allOf(withId(R.id.settings), withContentDescription("More"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.viewProfile), withText("View My Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.settingsButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.verifyPasswordEditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText7.perform(scrollTo(), replaceText("edit"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editUsernamePT), withText("editUser"),
                        withParent(allOf(withId(R.id.editusernameHLL),
                                withParent(withId(R.id.fragmentCL)))),
                        isDisplayed()));
        editText.check(matches(withText("editUser")));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.editUsernamePT), withText("editUser"),
                        childAtPosition(
                                allOf(withId(R.id.editusernameHLL),
                                        childAtPosition(
                                                withId(R.id.fragmentCL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("editUsers"));

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.editUsernamePT), withText("editUsers"),
                        childAtPosition(
                                allOf(withId(R.id.editusernameHLL),
                                        childAtPosition(
                                                withId(R.id.fragmentCL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.saveButton), withText("Save and Exit"),
                        childAtPosition(
                                allOf(withId(R.id.navButtonsHLL),
                                        childAtPosition(
                                                withId(R.id.fragmentCL),
                                                4)),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.usernameText), withText("editUsers"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("editUsers")));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.settingsButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.verifyPasswordEditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText10.perform(scrollTo(), replaceText("edit"), closeSoftKeyboard());

        ViewInteraction materialButton9 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton9.perform(scrollTo(), click());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.editUsernamePT), withText("editUsers"),
                        childAtPosition(
                                allOf(withId(R.id.editusernameHLL),
                                        childAtPosition(
                                                withId(R.id.fragmentCL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("editUser"));

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.editUsernamePT), withText("editUser"),
                        childAtPosition(
                                allOf(withId(R.id.editusernameHLL),
                                        childAtPosition(
                                                withId(R.id.fragmentCL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.exitButton), withText("Exit without Saving"),
                        childAtPosition(
                                allOf(withId(R.id.navButtonsHLL),
                                        childAtPosition(
                                                withId(R.id.fragmentCL),
                                                4)),
                                1),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction materialButton11 = onView(
                allOf(withId(android.R.id.button1), withText("Exit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton11.perform(scrollTo(), click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.usernameText), withText("editUsers"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView2.check(matches(withText("editUsers")));

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.settings), withContentDescription("More"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.delete), withText("Delete Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton12.perform(click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(android.R.id.button1), withText("Delete Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton13.perform(scrollTo(), click());
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
