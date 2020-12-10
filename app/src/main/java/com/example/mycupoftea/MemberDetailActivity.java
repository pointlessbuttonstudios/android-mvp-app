package com.example.mycupoftea;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.mycupoftea.member.Member;
import com.google.android.material.snackbar.Snackbar;

public class MemberDetailActivity extends AppCompatActivity
{
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

        relativeLayout = findViewById(R.id.relativelayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView profileImageView = findViewById(R.id.member_image);
        TextView nameTextView = findViewById(R.id.member_name);
        TextView positionTextView = findViewById(R.id.member_position);
        TextView personalityTextView = findViewById(R.id.member_personality);
        TextView interestsTextView = findViewById(R.id.member_interests);
        TextView preferencesTextView = findViewById(R.id.member_preferences);

        if(getIntent().getExtras().containsKey(Utility.MEMBER_TAG))
        {
            Member member = getIntent().getExtras().getParcelable(Utility.MEMBER_TAG);
            if (member != null)
            {
                Glide.with(this).load(member.getImage())
                        .apply(Utility.imageHandler())
                        .into(profileImageView);

                nameTextView.setText(member.getName());
                positionTextView.setText(member.getPosition());
                personalityTextView.setText(member.getPersonality());
                interestsTextView.setText(member.getInterests());
                preferencesTextView.setText(member.getPreferences());

                // Get member first name
                toolbar.setTitle("Meet " + (member.getName()).split(" ")[0]);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.member_detail_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
            case R.id.chat_action:
                Snackbar.make(relativeLayout, "Would open a messages activity!", Snackbar.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {
        finish();
    }
}