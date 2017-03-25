package com.yaseen.homage.fragments;

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

import com.yaseen.homage.R;
import com.yaseen.homage.models.Visit;
import com.yaseen.homage.models.VisitInfo;

import java.util.List;

/**
 * Created by macbookair on 3/25/17.
 */

public class VisitDetailsAdapter extends RecyclerView.Adapter<VisitDetailsAdapter.VisitDetailsVH> {


    private List<VisitInfo> mVisitinfo;
    private Context mContext;

    public VisitDetailsAdapter(List<VisitInfo> visitInfoList, Context context) {
        mVisitinfo = visitInfoList;
        mContext = context;
    }

    @Override
    public VisitDetailsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_visit_details, parent, false);
        return new VisitDetailsVH(holderView);
    }

    @Override
    public void onBindViewHolder(VisitDetailsVH holder, int position) {
        holder.primaryText.setText(mVisitinfo.get(position).getPrimaryText());
        holder.secondaryText.setText(mVisitinfo.get(position).getSecondaryText());
        holder.icon.setImageResource(mVisitinfo.get(position).getImageID());

    }

    @Override
    public int getItemCount() {
        return mVisitinfo.size();
    }

    public class VisitDetailsVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView icon;
        private TextView primaryText;
        private TextView secondaryText;

        public VisitDetailsVH(View itemView) {
            super(itemView);
            icon =(ImageView)itemView.findViewById(R.id.icon);
            primaryText=(TextView) itemView.findViewById(R.id.primary_text);
            secondaryText=(TextView)itemView.findViewById(R.id.secondary_text);

        }

        @Override
        public void onClick(View view) {


        }
    }
}
