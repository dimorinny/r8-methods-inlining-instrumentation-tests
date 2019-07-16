package com.dimorinny.proguard;

import android.content.Intent;

public class ClassForStaticMethod {

    public static Intent willBeInlined() {
        return new Intent();
    }
}
