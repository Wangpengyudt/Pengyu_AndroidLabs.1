package algonquin.cst2355.wang0532;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class week6TestingClass {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //unit test:
    @Test
    public void week6TestingClass() {
        //finding an object on screen:
        ViewInteraction appCompatEditText = onView(
                //all condition must be true;
                allOf(withId(R.id.editTextTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("fdsfdsfds"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.mybutton));


        materialButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextTextPassword), withText("fdsfdsfds"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("fdsf"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextTextPassword), withText("fdsf"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.mybutton), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editTextTextPassword), withText("fdsf"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editTextTextPassword), withText("fdsf"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(click());

        //change to check that matches "Login"
        ViewInteraction button = onView((withId(R.id.mybutton) ));


        button.check(matches(isDisplayed()));
    }

    /**checking for upperCase*/
    @Test
    public void week6TestingClassUpperCase(){
        //find the edittext:
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("ITITITIE"), closeSoftKeyboard());

        //click the button;
        //finding a new view;
        ViewInteraction materialButton = onView(withId(R.id.mybutton));
        //clicking the mouse on view R.id.button
        materialButton.perform(click());

        //assert
        //assertion
        ViewInteraction textView = onView(withId(R.id.textview));
        textView.check(matches(withText("Type in your password:")));
    }
    @Test
    public void week6TestingClassComplextEnough(){
        //find the editText;
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextTextPassword));
        appCompatEditText.perform(replaceText("abc%^"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.mybutton));
        materialButton.perform(click());
        ViewInteraction textView = onView(withId(R.id.textview));
        textView.check(matches(withText("Your password is complex enough")));
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
