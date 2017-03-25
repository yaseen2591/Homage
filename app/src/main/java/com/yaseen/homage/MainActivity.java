package com.yaseen.homage;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.yaseen.homage.fragments.GetCareFragment;
import com.yaseen.homage.fragments.RecipientsFragment;
import com.yaseen.homage.fragments.SettingsFragment;
import com.yaseen.homage.fragments.VisitDetailsFragment;
import com.yaseen.homage.fragments.VisitsFragment;
import com.yaseen.homage.util.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity implements GetCareFragment.OnFragmentInteractionListener,
        RecipientsFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, VisitsFragment.OnFragmentInteractionListener, VisitDetailsFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;
    private GetCareFragment mGetCareFragment;
    private RecipientsFragment mRecipientsFragment;
    private SettingsFragment mSettingsFragment;
    private VisitsFragment mVisitsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (mGetCareFragment == null) {
                        fragment = mGetCareFragment = new GetCareFragment();
                    } else {
                        fragment = mGetCareFragment;
                    }
                    break;
                case R.id.navigation_visits:
                    if (mVisitsFragment == null) {
                        fragment = mVisitsFragment = new VisitsFragment();
                    } else {
                        fragment = mVisitsFragment;
                    }
                    break;
                case R.id.navigation_recipient:
                    if (mRecipientsFragment == null) {
                        fragment = mRecipientsFragment = new RecipientsFragment();
                    } else {
                        fragment = mRecipientsFragment;
                    }
                    break;
                case R.id.navigation_settings:
                    if (mSettingsFragment == null) {
                        fragment = mSettingsFragment = new SettingsFragment();
                    } else {
                        fragment = mSettingsFragment;
                    }
            }
            if (fragment != null) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
            }


            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        if (mGetCareFragment == null) {
            mGetCareFragment = new GetCareFragment();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, mGetCareFragment);
        fragmentTransaction.commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    getSupportActionBar().setTitle("Homage");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
