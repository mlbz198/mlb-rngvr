package com.luongnd.RNGvr;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

public class PanoramaViewManager extends SimpleViewManager<PanoramaView> {
    private static final String REACT_CLASS = "RNGoogleVRPanorama";

    private ReactApplicationContext _context;

    public PanoramaViewManager(ReactApplicationContext context) {
        super();
        _context = context;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public PanoramaView createViewInstance(ThemedReactContext context) {
        return new PanoramaView(context, this, context.getCurrentActivity());
    }

    @Override
    protected void onAfterUpdateTransaction(PanoramaView view) {
        super.onAfterUpdateTransaction(view);
        view.onAfterUpdateTransaction();
    }

    public @Nullable Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
            .put("onImageLoaded", MapBuilder.of("registrationName", "onImageLoaded"))
            .put("onImageLoadingFailed", MapBuilder.of("registrationName", "onImageLoadingFailed"))
            .build();
    }

    public ReactApplicationContext getContext() {
        return _context;
    }

    @ReactProp(name = "imageUrl")
    public void setImageUrl(PanoramaView view, String imageUrl) {
        view.setImageUrl(imageUrl);
    }

    @ReactProp(name = "dimensions")
    public void setDimensions(PanoramaView view, ReadableMap dimensions) {
        int width = dimensions.getInt("width");
        int height = dimensions.getInt("height");
        view.setDimensions(width, height);
    }

    @ReactProp(name = "inputType")
    public void setInputType(PanoramaView view, int type) {
        // view.setInputType();
    }
}
