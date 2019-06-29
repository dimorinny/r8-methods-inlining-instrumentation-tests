### Run instrumentation tests with minified applications

We're going to run instrumentation tests along with production (minified) application.
We want to cover all cases (including incorrect proguard (r8) configurations) and test exactly
the same APK, that will be released to our users.

But now we have several issues mostly about inconsistent between methods from proguarded production APK
and method references from test APK. 

### Bug

Right now I want to consider R8 inlining issue.

We have application, that have 2 static methods:

```java
com.dimorinny.proguard.ClassForStaticMethod.willBeInlined
com.dimorinny.proguard.MainActivity.willBeInlined
```

That methods are used not only in main APK, but also in test APK. As I understand right, that
allowed by classpath merging between tested and test APKs during running instrumentation tests.

R8 compiler has method inlining feature, that removes methods from final APK, but test APK still
have method references, that must be resolved from APK during runtime. But it is impossible,
because, that methods have been already inlined by R8.  

### How to reproduce?

1. Clone project:
```
git clone https://github.com/dimorinny/r8-methods-inlining-instrumentation-tests.git
```

2. Build test and target APKs
```
./gradlew app:assembleAndroidTest
```

3. Open target APK (`app/build/outputs/apk/release/app-release-unsigned.apk`), and you will able
to see, that `ClassForStaticMethod` was inlined, and all their code can be found exactly in call place.

4. Open test APK (`app/build/outputs/apk/androidTest/release/app-release-androidTest.apk`), and you 
will able to see method references to already inlined class `ClassForStaticMethod`.

5. Sign APKs by`jarsigner`

### Definition of done

Test APK compilation process must be know more about minification process in tested APK compilation.
Exactly in this example, that method must be inlined not only in tested APK, but also in test APK.