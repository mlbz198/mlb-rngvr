package com.luongnd.RNGvr;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * PVideoPackage.java
 *
 * Created by Pietralberto Mazza on 22/06/17.
 * Copyright © 2017 Facebook. All rights reserved.
 *
 */

public class RNGvrPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    // // Deprecated RN 0.47
     public List<Class<? extends JavaScriptModule>> createJSModules() {
         return Collections.emptyList();
     }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(new VrVideoManager(reactContext), new PanoramaViewManager(reactContext));
    }
}
