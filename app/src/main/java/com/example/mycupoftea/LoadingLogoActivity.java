package com.example.mycupoftea;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.mycupoftea.member.MemberViewModel;

/**
 * Purpose:
 * To display an app logo/tag line to the user while preloading data
 * in the background before moving onto core app
 */
public class LoadingLogoActivity extends AppCompatActivity
{
    private final Context context = this;
    private long millisInFuture = 1000;
    private long countDownInterval = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Utility.init(this, ViewModelProviders.of(this).get(MemberViewModel.class));
        /*
        Note:
        For simplicity I'm using this timer, but normally this would be an AsyncTask call with
        a delegate that controls onProcessFinish()
        */
        /*new CountDownTimer(millisInFuture, countDownInterval)
        {
            @Override
            public void onTick(long millisUntilFinished) { }
            @Override
            public void onFinish()
            {
                Intent intent = new Intent(context, MemberListActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();*/
    }
}