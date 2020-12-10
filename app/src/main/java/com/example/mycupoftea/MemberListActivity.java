package com.example.mycupoftea;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycupoftea.member.Member;
import com.example.mycupoftea.member.MemberViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MemberListActivity extends AppCompatActivity
{
    private MemberListAdapter memberListAdapter;
    private MemberViewModel memberViewModel;
    private FloatingActionButton fab;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.meet_the_team);
        setSupportActionBar(toolbar);

        RecyclerView teamRecyclerView = findViewById(R.id.team_recyclerview);
        fab = findViewById(R.id.fab);

        memberListAdapter = new MemberListAdapter(this);
        teamRecyclerView.setAdapter(memberListAdapter);
        teamRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Only has ability to add Amina as a member, if she has not been added before
        if(!Utility.hasAddedMember())
        {
            fab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Snackbar.make(v, "Amina has been added!", Snackbar.LENGTH_LONG).show();
                    Member amina = new Member(
                            "12",
                            "Amina Khalique",
                            "Android Developer",
                            "https://aminakhaliquecom.files.wordpress.com/2020/10/amina.jpeg",
                            "Coffee addicted quirk-ball who's prone to laughing fits and is actually genuinely interested in your Spotify playlist",
                            "The kind who loved walks before the pandemic! Gaming, DND, Horror/Psychological/Crime Novels, Manga / Manhwa, Anime - yep I'm that person...",
                            "Are you kind? Cuz that REALLY works for me :) Also married though");
                    memberViewModel.insert(amina);
                    fab.setVisibility(View.GONE);
                    Utility.setAddedMember(true);
                    memberListAdapter.notifyDataSetChanged();
                }
            });
            fab.setVisibility(View.VISIBLE);
        }
        memberViewModel = Utility.getMemberViewModel();
        memberViewModel.getAllMembers().observe(this, new Observer<List<Member>>()
        {
            @Override
            public void onChanged(List<Member> members)
            {
                memberListAdapter.setMembers(members);
                memberListAdapter.notifyDataSetChanged();
            }
        });
    }
}