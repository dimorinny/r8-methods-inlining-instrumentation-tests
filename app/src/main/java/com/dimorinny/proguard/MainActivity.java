package com.dimorinny.proguard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static Intent willBeInlined(String unusedArgument) {
        return new Intent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassForStaticMethod.willBeInlined();
        willBeInlined("unused");
    }
}
