package com.example.mycupoftea;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mycupoftea.member.Member;

import java.util.ArrayList;
import java.util.List;
public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberListHolder>
{
    private Context context;
    private final LayoutInflater inflater;
    private List<Member> teamMembers = new ArrayList<>();
    public MemberListAdapter(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }
    /* VIEW HOLDER START */
    public class MemberListHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView memberName;
        private final TextView memberPosition;
        private final ImageView memberImage;
        public MemberListHolder(@NonNull View itemView)
        {
            super(itemView);

            memberName = itemView.findViewById(R.id.name);
            memberPosition = itemView.findViewById(R.id.position);
            memberImage = itemView.findViewById(R.id.profile_image);

            memberImage.setColorFilter(Utility.transformBW());

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, MemberDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(Utility.MEMBER_TAG, teamMembers.get(getAdapterPosition()));
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
    /* VIEW HOLDER END */
    @NonNull
    @Override
    public MemberListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = inflater.inflate(R.layout.list_item_member, parent, false);
        return new MemberListHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MemberListHolder holder, int position)
    {
        if(teamMembers != null)
        {
            Member member = teamMembers.get(position);
            holder.memberPosition.setText(member.getPosition());
            holder.memberName.setText(member.getName());

            Glide.with(context).load(member.getImage())
                    .apply(Utility.imageHandler())
                    .into(holder.memberImage);
        }
    }
    @Override
    public int getItemCount()
    {
        if(teamMembers != null)
        {
            return teamMembers.size();
        }
        else return 0;
    }
    public void setMembers(List<Member> members)
    {
        this.teamMembers = members;
    }
}
