package com.luongnd.RNGvr;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

/**
 * VrVideoManager.java
 *
 * Created by Pietralberto Mazza on 22/06/17.
 * Copyright © 2017 Facebook. All rights reserved.
 *
 */

public class VrVideoManager extends SimpleViewManager<VrVideoView> {
    private static final String CLASS_NAME = "VrVideo";
    private static final String TAG = VrVideoManager.class.getSimpleName();

    private VrVideoView view;

    public VrVideoManager(ReactApplicationContext context) { super(); }

    @Override
    public String getName() {
        return CLASS_NAME;
    }

    @Override
    protected VrVideoView createViewInstance(ThemedReactContext context) {
        view = new VrVideoView(context.getCurrentActivity());

        view.setEventListener(new ActivityEventListener());

        return view;
    }

    @Override
    public void onDropViewInstance(VrVideoView view) {
        super.onDropViewInstance(view);
        view.pauseVideo();
        view = null;
    }

    @ReactProp(name = "displayMode")
    public void setDisplayMode(VrVideoView view, String mode) {
        switch(mode) {
            case "embedded":
                view.setDisplayMode(VrWidgetView.DisplayMode.EMBEDDED);
                break;
            case "fullscreen":
                view.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
                break;
            case "cardboard":
                view.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_STEREO);
                break;
            default:
                view.setDisplayMode(VrWidgetView.DisplayMode.EMBEDDED);
                break;
        }
    }

    @ReactProp(name = "volume")
    public void setVolume(VrVideoView view, float value) {
        view.setVolume(value);
    }

    @ReactProp(name = "enableFullscreenButton")
    public void setFullscreenButtonEnabled(VrVideoView view, Boolean enabled) {
        view.setFullscreenButtonEnabled(enabled);
    }

    @ReactProp(name = "enableCardboardButton")
    public void setCardboardButtonEnabled(VrVideoView view, Boolean enabled) {
        view.setStereoModeButtonEnabled(enabled);
    }

    @ReactProp(name = "enableTouchTracking")
    public void setTouchTrackingEnabled(VrVideoView view, Boolean enabled) {
        view.setTouchTrackingEnabled(enabled);
    }

    @ReactProp(name = "hidesTransitionView")
    public void setTransitionViewEnabled(VrVideoView view, Boolean enabled) {
        view.setTransitionViewEnabled(!enabled);
    }

    @ReactProp(name = "enableInfoButton")
    public void setInfoButtonEnabled(VrVideoView view, Boolean enabled) {
        view.setInfoButtonEnabled(enabled);
    }

    @ReactProp(name = "src")
    public void setSrc(VrVideoView view, ReadableMap src) {

        String type = src.getString("type");
        String uri = src.getString("uri");
        Boolean isNetwork = src.getBoolean("isNetwork");
        Boolean isAsset = src.getBoolean("isAsset");

        VrVideoView.Options videoOptions = new VrVideoView.Options();
        videoOptions.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;

        switch(type) {
            case "mono":
                videoOptions.inputType = VrVideoView.Options.TYPE_MONO;
                break;
            case "stereo":
                videoOptions.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
                break;
            default:
                videoOptions.inputType = VrVideoView.Options.TYPE_MONO;
                break;
        }
        Source source = new Source(uri, videoOptions, isNetwork, isAsset);
        VideoLoaderTask videoLoaderTask = new VideoLoaderTask();
        videoLoaderTask.execute(source);
    }

    private class ActivityEventListener extends VrVideoEventListener {
        @Override
        public void onLoadSuccess() {

            Log.i(TAG, "Successfully loaded video " + view.getDuration());
        }

        /**
         * Called by video widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
            // An error here is normally due to being unable to decode the video format.
            Log.e(TAG, "Error loading video: " + errorMessage);
        }

        /**
         * Update the UI every frame.
         */
        @Override
        public void onNewFrame() {

        }

        /**
         * Make the video play in a loop. This method could also be used to move to the next video in
         * a playlist.
         */
        @Override
        public void onCompletion() {
            if(view != null) view.seekTo(0);
        }
    }

    class Source {
        public String uri;
        public VrVideoView.Options options;
        public Boolean isAsset;
        public Boolean isNetwork;

        public Source(String uri, VrVideoView.Options videoOptions, Boolean isNetwork, Boolean isAsset) {
            this.uri = uri;
            this.options = videoOptions;
            this.isNetwork = isNetwork;
            this.isAsset = isAsset;
        }
    }

    class VideoLoaderTask extends AsyncTask<Source, Void, Boolean> {
        @SuppressWarnings("WrongThread")
        protected Boolean doInBackground(Source... args) {
            try {
                if (args[0].isAsset) {
                    view.loadVideoFromAsset(args[0].uri, args[0].options);
                } else {
                    Uri uri = Uri.parse(args[0].uri);
                    view.loadVideo(uri, args[0].options);
                }
            } catch (IOException e) {}

            return true;
        }
    }
}
