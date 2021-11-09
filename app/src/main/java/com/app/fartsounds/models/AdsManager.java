package com.app.fartsounds.models;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.fartsounds.R;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class AdsManager {
    public static IronSourceBannerLayout banner;

    public static void bannerAd(Activity activity){
        if(banner != null){
            IronSource.destroyBanner(banner);
            LinearLayout bannerContainer = activity.findViewById(R.id.linearAd);
            banner = IronSource.createBanner(activity, ISBannerSize.SMART);
            bannerContainer.addView(banner);
            IronSource.loadBanner(banner);

        }
        else {

            LinearLayout bannerContainer = activity.findViewById(R.id.linearAd);
            banner = IronSource.createBanner(activity, ISBannerSize.SMART);
            bannerContainer.addView(banner);
            IronSource.loadBanner(banner);
        }

    }


    public static void loadIntersAd(TextView textView, ProgressBar progressBar){
        IronSource.loadInterstitial();
        IronSource.setInterstitialListener(new InterstitialListener() {
            /**
             * Invoked when Interstitial Ad is ready to be shown after load function was called.
             */
            @Override
            public void onInterstitialAdReady() {
                textView.setText(R.string.continue_button);
                progressBar.setVisibility(View.GONE);

            }
            /**
             * invoked when there is no Interstitial Ad available after calling load function.
             */
            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                textView.setText(R.string.continue_button);
                progressBar.setVisibility(View.GONE);
            }
            /**
             * Invoked when the Interstitial Ad Unit is opened
             */
            @Override
            public void onInterstitialAdOpened() {
            }
            /*
             * Invoked when the ad is closed and the user is about to return to the application.
             */
            @Override
            public void onInterstitialAdClosed() {
            }
            /**
             * Invoked when Interstitial ad failed to show.
             * @param error - An object which represents the reason of showInterstitial failure.
             */
            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {
            }
            /*
             * Invoked when the end user clicked on the interstitial ad, for supported networks only.
             */
            @Override
            public void onInterstitialAdClicked() {
            }
            /** Invoked right before the Interstitial screen is about to open.
             *  NOTE - This event is available only for some of the networks.
             *  You should NOT treat this event as an interstitial impression, but rather use InterstitialAdOpenedEvent
             */
            @Override
            public void onInterstitialAdShowSucceeded() {
            }
        });

    }

    public static void showNext(Activity activity, Class c){
        if(IronSource.isInterstitialReady()){
            IronSource.removeInterstitialListener();
            IronSource.setInterstitialListener(new InterstitialListener() {
                /**
                 * Invoked when Interstitial Ad is ready to be shown after load function was called.
                 */
                @Override
                public void onInterstitialAdReady() {


                }
                /**
                 * invoked when there is no Interstitial Ad available after calling load function.
                 */
                @Override
                public void onInterstitialAdLoadFailed(IronSourceError error) {

                }
                /**
                 * Invoked when the Interstitial Ad Unit is opened
                 */
                @Override
                public void onInterstitialAdOpened() {
                }
                /*
                 * Invoked when the ad is closed and the user is about to return to the application.
                 */
                @Override
                public void onInterstitialAdClosed() {

                    activity.startActivity(new Intent(activity,c));
                    IronSource.loadInterstitial();
                    activity.finish();

                }
                /**
                 * Invoked when Interstitial ad failed to show.
                 * @param error - An object which represents the reason of showInterstitial failure.
                 */
                @Override
                public void onInterstitialAdShowFailed(IronSourceError error) {
                }
                /*
                 * Invoked when the end user clicked on the interstitial ad, for supported networks only.
                 */
                @Override
                public void onInterstitialAdClicked() {
                }
                /** Invoked right before the Interstitial screen is about to open.
                 *  NOTE - This event is available only for some of the networks.
                 *  You should NOT treat this event as an interstitial impression, but rather use InterstitialAdOpenedEvent
                 */
                @Override
                public void onInterstitialAdShowSucceeded() {
                }
            });
            IronSource.showInterstitial();

        }
        else {

            activity.startActivity(new Intent(activity,c));
            IronSource.loadInterstitial();
            activity.finish();

        }

    }

}
