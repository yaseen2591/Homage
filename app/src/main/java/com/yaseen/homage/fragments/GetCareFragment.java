package com.yaseen.homage.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaseen.homage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetCareFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    public GetCareFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_get_care, container, false);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GetCareFragment.OnFragmentInteractionListener) {
            mListener = (GetCareFragment.OnFragmentInteractionListener) context;
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
