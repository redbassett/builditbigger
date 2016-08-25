package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    InterstitialAd mInstersitialAd;
    Button mButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mButton = (Button) root.findViewById(R.id.this_button_tells_jokes);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInstersitialAd.isLoaded()) {
                    mInstersitialAd.show();
                } else {
                    ((MainActivity) getActivity()).tellJoke(v);
                }
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInstersitialAd = new InterstitialAd(getActivity());
        mInstersitialAd.setAdUnitId(getString(R.string.intersitial_ad_unit_id));
        mInstersitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewIntersitialAd();
                ((MainActivity) getActivity()).tellJoke(mButton);
            }
        });

        requestNewIntersitialAd();

        return root;
    }

    private void requestNewIntersitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInstersitialAd.loadAd(adRequest);
    }


}
