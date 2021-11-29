package com.example.cmput_301_project;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.cmput_301_project.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAccountValidationTest {

    @Rule
    public ActivityTestRule<LoginScreenPage> mActivityTestRule = new ActivityTestRule<>(LoginScreenPage.class);

    @Test
    public void createAccountValidationTest() {
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
        appCompatEditText.perform(replaceText("badTestEmail@mail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.createUsernameEV),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("badTest"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.createPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.createPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("bad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.reenterPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.reenterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("bad"), closeSoftKeyboard());

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

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        childAtPosition(
                                allOf(withId(R.id.activityNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.createEmailEV),
                        childAtPosition(
                                allOf(withId(R.id.createEmailHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("badTestEmail@mail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.createUsernameEV),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("badTest2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.createPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.createPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("bad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.reenterPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.reenterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(longClick());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.reenterPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.reenterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("bad"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.fragmentNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("This email is already paired to an existing account"),
                        withParent(withParent(withId(R.id.scrollView))),
                        isDisplayed()));
        textView.check(matches(withText("This email is already paired to an existing account")));

        ViewInteraction materialButton6 = onView(
                allOf(withId(android.R.id.button2), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.createEmailEV), withText("badTestEmail@mail.com"),
                        childAtPosition(
                                allOf(withId(R.id.createEmailHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("badTestEmail@mail.ca"));

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.createEmailEV), withText("badTestEmail@mail.ca"),
                        childAtPosition(
                                allOf(withId(R.id.createEmailHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.createUsernameEV), withText("badTest2"),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("badTest"));

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.createUsernameEV), withText("badTest"),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText13.perform(closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.fragmentNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.message), withText("This username is already taken"),
                        withParent(withParent(withId(R.id.scrollView))),
                        isDisplayed()));
        textView2.check(matches(withText("This username is already taken")));

        ViewInteraction materialButton8 = onView(
                allOf(withId(android.R.id.button2), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.createPasswordEV), withText("bad"),
                        childAtPosition(
                                allOf(withId(R.id.createPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText14.perform(replaceText(""));

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.createPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.createPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText15.perform(closeSoftKeyboard());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.fragmentNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.alertTitle), withText("Missing Required Field"),
                        withParent(allOf(withId(R.id.title_template),
                                withParent(withId(R.id.topPanel)))),
                        isDisplayed()));
        textView3.check(matches(withText("Missing Required Field")));

        ViewInteraction materialButton10 = onView(
                allOf(withId(android.R.id.button2), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton10.perform(scrollTo(), click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.createPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.createPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText16.perform(replaceText("Badd"), closeSoftKeyboard());

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.fragmentNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(android.R.id.button2), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton12.perform(scrollTo(), click());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.createUsernameEV), withText("badTest"),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("badTest2"));

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.createUsernameEV), withText("badTest2"),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText18.perform(closeSoftKeyboard());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.fragmentNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton13.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.alertTitle), withText("Passwords Do Not Match"),
                        withParent(allOf(withId(R.id.title_template),
                                withParent(withId(R.id.topPanel)))),
                        isDisplayed()));
        textView4.check(matches(withText("Passwords Do Not Match")));

        ViewInteraction materialButton14 = onView(
                allOf(withId(android.R.id.button2), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton14.perform(scrollTo(), click());

        ViewInteraction materialButton15 = onView(
                allOf(withId(R.id.cancelButton), withText("Cancel"),
                        childAtPosition(
                                allOf(withId(R.id.fragmentNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton15.perform(click());

        ViewInteraction materialButton16 = onView(
                allOf(withId(android.R.id.button1), withText("Exit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton16.perform(scrollTo(), click());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.usernameEV),
                        childAtPosition(
                                allOf(withId(R.id.enterUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText19.perform(replaceText("badTest"), closeSoftKeyboard());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.passwordEV),
                        childAtPosition(
                                allOf(withId(R.id.enterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText20.perform(replaceText("bad"), closeSoftKeyboard());

        ViewInteraction materialButton17 = onView(
                allOf(withId(R.id.signInButton), withText("Sign In"),
                        childAtPosition(
                                allOf(withId(R.id.activityNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton17.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.settings), withContentDescription("More"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton18 = onView(
                allOf(withId(R.id.delete), withText("Delete Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton18.perform(click());

        ViewInteraction materialButton19 = onView(
                allOf(withId(android.R.id.button1), withText("Delete Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton19.perform(scrollTo(), click());
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
