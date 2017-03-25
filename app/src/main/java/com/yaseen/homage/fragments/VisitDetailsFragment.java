package com.yaseen.homage.fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaseen.homage.R;
import com.yaseen.homage.models.Visit;
import com.yaseen.homage.models.VisitInfo;
import com.yaseen.homage.util.CircleTransform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VisitDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class VisitDetailsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Visit mVisit;
    private ImageView profileImage;
    private TextView caregiverName;
    private TextView estimation;
    private List<VisitInfo> mVisitInfoList=new ArrayList<>();
    private RecyclerView mRecyclerview;
    private VisitDetailsAdapter mAdapter;

    public VisitDetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visit_details, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Visit Info");
        profileImage = (ImageView) view.findViewById(R.id.profile_photo);
        caregiverName = (TextView) view.findViewById(R.id.username);
        mRecyclerview=(RecyclerView)view.findViewById(R.id.recyclerview);

        if (mVisit != null) {
            Glide.with(getActivity()).load(mVisit.getProfilePhoto()).transform(new CircleTransform(getActivity())).into(profileImage);
            caregiverName.setText(mVisit.getUsername());

            VisitInfo date= new VisitInfo();
            date.setPrimaryText("Date");
            date.setSecondaryText(mVisit.getDate());
            date.setImageID(R.drawable.ic_event_black_24dp);

            VisitInfo address= new VisitInfo();
            address.setPrimaryText("Address");
            address.setSecondaryText(mVisit.getAddress());
            address.setImageID(R.drawable.ic_room_black_24dp);

            VisitInfo instructions= new VisitInfo();
            instructions.setPrimaryText("Instructions");
            instructions.setSecondaryText(mVisit.getInstructions());
            instructions.setImageID(R.drawable.ic_insert_comment_black_24dp);

            VisitInfo estimation= new VisitInfo();
            estimation.setPrimaryText("Billing Estimate");
            estimation.setImageID(R.drawable.ic_credit_card_black_24dp);
            estimation.setSecondaryText(mVisit.getBillingEstimation());

            mVisitInfoList.add(date);
            mVisitInfoList.add(address);
            mVisitInfoList.add(instructions);
            mVisitInfoList.add(estimation);

        } else {
            Glide.clear(profileImage);
            Glide.with(getActivity()).load(R.drawable.ic_care_pro_user);
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerview.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerview.addItemDecoration(dividerItemDecoration);
        mAdapter = new VisitDetailsAdapter(mVisitInfoList, (AppCompatActivity) getActivity());

        mRecyclerview.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null)
            mVisit = (Visit) bundle.getSerializable("visit_info");


        setHasOptionsMenu(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            getActivity().getSupportFragmentManager().popBackStack();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Homage");
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
