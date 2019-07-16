package com.dimorinny.proguard;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.dimorinny.proguard.ClassForStaticMethod.willBeInlined;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        MainActivity.willBeInlined("unused");
        rule.launchActivity(willBeInlined());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
