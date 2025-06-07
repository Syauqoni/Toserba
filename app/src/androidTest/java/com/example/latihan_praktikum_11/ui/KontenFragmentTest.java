package com.example.latihan_praktikum_11.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;

import android.Manifest;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import com.example.latihan_praktikum_11.R;
import com.example.latihan_praktikum_11.presentation.ui.auth.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class KontenFragmentTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Rule
    public GrantPermissionRule permissionRule =
            GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    @Test
    public void testTambahProdukBerhasil() throws InterruptedException {
        onView(withId(R.id.etUsernameOrEmail)).perform(typeText("syauqoni31@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.etPassword)).perform(typeText("secreat31"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogin)).perform(click());

        Thread.sleep(2000);

        onView(withId(R.id.menu_konten)).check(matches(isDisplayed()));
        onView(withId(R.id.menu_konten)).perform(click());

        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()));
        onView(withId(R.id.fabAdd)).perform(click());

        onView(withId(R.id.etNamaProduk)).perform(typeText("Test Produk"));
        closeSoftKeyboard();
        onView(withId(R.id.etHargaProduk)).perform(typeText("5000"));
        closeSoftKeyboard();

        Thread.sleep(1000);
        onView(withId(R.id.btnSimpanProduk)).perform(click());

        onView(withId(R.id.rvProduk)).perform(swipeUp());

        onView(withId(R.id.rvProduk))
                .perform(scrollTo(hasDescendant(withText("Test Produk"))));

        Thread.sleep(3000);
        onView(withText("Test Produk")).check(matches(isDisplayed()));
    }
}
