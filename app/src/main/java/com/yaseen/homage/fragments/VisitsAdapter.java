package com.yaseen.homage.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaseen.homage.R;
import com.yaseen.homage.models.Visit;
import com.yaseen.homage.util.CircleTransform;

import java.util.List;

/**
 * Created by macbookair on 3/25/17.
 */

public class VisitsAdapter extends RecyclerView.Adapter<VisitsAdapter.VisitsVH> {

    private List<Visit> mVisits;
    private AppCompatActivity mContext;

    public VisitsAdapter(List<Visit> visits, AppCompatActivity context) {
        mVisits = visits;
        mContext = context;
    }


    @Override
    public VisitsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_visit, parent, false);
        return new VisitsVH(holderView);
    }

    @Override
    public void onBindViewHolder(VisitsVH holder, int position) {
        Visit visit=mVisits.get(position);
        holder.username.setText(visit.getUsername());
        Glide.with(mContext).load(visit.getProfilePhoto()).transform(new CircleTransform(mContext)).into(holder.profilePhoto);

    }

    @Override
    public int getItemCount() {
        return mVisits.size();
    }

    public class VisitsVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView profilePhoto;
        private TextView username;

        public VisitsVH(View itemView) {
            super(itemView);
            profilePhoto=(ImageView)itemView.findViewById(R.id.profile_photo);
            username=(TextView) itemView.findViewById(R.id.username);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
            VisitDetailsFragment visitDetailsFragment = new VisitDetailsFragment();
            Bundle arguments = new Bundle();
            arguments.putSerializable("visit_info", mVisits.get(getAdapterPosition()));
            visitDetailsFragment.setArguments(arguments);
            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            ft.replace(R.id.content,visitDetailsFragment,"VisitDetails").addToBackStack("VisitDetails").commit();

        }
    }
}
