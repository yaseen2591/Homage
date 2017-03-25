package com.yaseen.homage.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yaseen.homage.MainActivity;
import com.yaseen.homage.R;
import com.yaseen.homage.models.Visit;
import com.yaseen.homage.rest.RestApiService;
import com.yaseen.homage.rest.RestServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VisitsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class VisitsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerview;
    private ProgressBar mProgressbar;
    private LinearLayout error_wrapper;
    private FloatingActionButton simulateButton;
    private List<Visit> mVisits = new ArrayList<>();
    private VisitsAdapter mAdapter;

    public VisitsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visits, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.visits_recyclerview);
        mProgressbar = (ProgressBar) view.findViewById(R.id.progress_bar);
        error_wrapper = (LinearLayout) view.findViewById(R.id.error_wrapper);
        simulateButton = (FloatingActionButton) view.findViewById(R.id.simulate_launcher);

        simulateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressbar.setVisibility(View.VISIBLE);
                error_wrapper.setVisibility(View.GONE);
                getVisits();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerview.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerview.addItemDecoration(dividerItemDecoration);
        mAdapter = new VisitsAdapter(mVisits, (AppCompatActivity) getActivity());

        mRecyclerview.setAdapter(mAdapter);
        reloadData();

        return view;
    }

    private void reloadData() {
        if (mVisits.size() > 0) {
            mAdapter.notifyDataSetChanged();
            mProgressbar.setVisibility(View.GONE);
            error_wrapper.setVisibility(View.GONE);
        } else {
            mAdapter.notifyDataSetChanged();
            mProgressbar.setVisibility(View.GONE);
            error_wrapper.setVisibility(View.VISIBLE);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void getVisits() {
        RestServiceInterface apiService =
                RestApiService.getClient().create(RestServiceInterface.class);

        Call<String> call = apiService.getVisits();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("Visits", response.body());

                try {
                    JSONObject result = new JSONObject(response.body());
                    mVisits.clear();
                    JSONArray visitsData = result.getJSONArray("visits");
                    for (int i = 0; i < visitsData.length(); i++) {
                        Visit visit = new Visit();
                        JSONObject care_pro_info = visitsData.getJSONObject(i).getJSONObject("care_pro_info");
                        JSONObject visit_info = visitsData.getJSONObject(i).getJSONObject("visit_info");

                        visit.setUsername(care_pro_info.getString("username"));
                        visit.setProfilePhoto(care_pro_info.getString("profile_photo"));
                        visit.setAddress(visit_info.getString("address"));
                        visit.setDate(visit_info.getString("date"));
                        visit.setInstructions(visit_info.getString("instructions"));
                        visit.setBillingEstimation(visit_info.getString("billing-estimate"));
                        mVisits.add(visit);
                        reloadData();
                    }

                    createNotification();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Visits", "failed to load data");
            }
        });
    }

    private void createNotification() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(getActivity());

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_care_pro_user)
                .setContentTitle("New Visit")
                .setContentText("you've received " + String.valueOf(mVisits.size()) + " new Visits")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");


        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
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
