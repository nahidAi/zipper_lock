package com.valentines.zipperlock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_about);
        View aboutPage = new AboutPage(this)
                .isRTL(true)
                .setImage(R.drawable.about_icon_email)
                .addEmail("elmehdi.sakout@gmail.com")
                .addWebsite("newskylearn20.ir")
                .addInstagram("n.a.mehr")
                .setDescription("خرید راحت و ارزان با فروشگاه اینترنتی فروشگاه من ")
                .create();
        setContentView(aboutPage);
    }
}
