package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import java.util.concurrent.TimeUnit;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mActivity;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    /* https://discussions.udacity.com/t/writing-tests-for-async-task/25482/10 */
    @MediumTest
    public void testRetrievesJoke() {
        String joke;

        try {
            MainActivity.EndpointsAsyncTask jokeTask = new MainActivity.EndpointsAsyncTask();
            jokeTask.execute(getActivity());
            joke = jokeTask.get(30, TimeUnit.SECONDS);

            if (joke.isEmpty())
                fail("Joke string empty");

        } catch (Exception e) {
            fail("Timed out");
        }
    }
}
