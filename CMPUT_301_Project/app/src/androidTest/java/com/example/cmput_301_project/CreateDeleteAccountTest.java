package com.example.cmput_301_project;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
public class CreateDeleteAccountTest {

    @Rule
    public ActivityTestRule<LoginScreenPage> mActivityTestRule = new ActivityTestRule<>(LoginScreenPage.class);

    @Test
    public void createDeleteAccountTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.usernameEV),
                        childAtPosition(
                                allOf(withId(R.id.enterUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("newUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.passwordEV),
                        childAtPosition(
                                allOf(withId(R.id.enterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.signInButton), withText("Sign In"),
                        childAtPosition(
                                allOf(withId(R.id.activityNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.alertTitle), withText("Username/Password combination does not exist"),
                        withParent(allOf(withId(R.id.title_template),
                                withParent(withId(R.id.topPanel)))),
                        isDisplayed()));
        textView.check(matches(withText("Username/Password combination does not exist")));

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button2), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        childAtPosition(
                                allOf(withId(R.id.activityNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.createEmailEV),
                        childAtPosition(
                                allOf(withId(R.id.createEmailHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("newUser@mail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.createUsernameEV),
                        childAtPosition(
                                allOf(withId(R.id.createUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("newUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.createPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.createPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.reenterPasswordEV),
                        childAtPosition(
                                allOf(withId(R.id.reenterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.pinputPromptsVLL),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.signUpButton), withText("Sign Up"),
                        childAtPosition(
                                allOf(withId(R.id.fragmentNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.alertTitle), withText("Account Creation Successful"),
                        withParent(allOf(withId(R.id.title_template),
                                withParent(withId(R.id.topPanel)))),
                        isDisplayed()));
        textView2.check(matches(withText("Account Creation Successful")));

        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button2), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.usernameEV),
                        childAtPosition(
                                allOf(withId(R.id.enterUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("newUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.passwordEV),
                        childAtPosition(
                                allOf(withId(R.id.enterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.signInButton), withText("Sign In"),
                        childAtPosition(
                                allOf(withId(R.id.activityNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.updateButton), withText("Daily Update"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.settings), withContentDescription("More"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.delete), withText("Delete Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(android.R.id.button1), withText("Delete Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.registerPromptTV), withText("Don't have an account yet? Register!"),
                        withParent(allOf(withId(R.id.signinPromptsVLL),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView3.check(matches(withText("Don't have an account yet? Register!")));

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.usernameEV),
                        childAtPosition(
                                allOf(withId(R.id.enterUsernameHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("newUser"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.passwordEV),
                        childAtPosition(
                                allOf(withId(R.id.enterPasswordHLL),
                                        childAtPosition(
                                                withId(R.id.signinPromptsVLL),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.signInButton), withText("Sign In"),
                        childAtPosition(
                                allOf(withId(R.id.activityNavButtonsHLL),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.alertTitle), withText("Username/Password combination does not exist"),
                        withParent(allOf(withId(R.id.title_template),
                                withParent(withId(R.id.topPanel)))),
                        isDisplayed()));
        textView4.check(matches(withText("Username/Password combination does not exist")));
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
