//
//  PanoramaView.h
//  panorama
//
//  Created by Marco Argentieri on 28/12/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTView.h>
// #import "RCTEventDispatcher.h"
#import "GVRVideoView.h"

@class RCTEventDispatcher;

@interface VrVideoView : RCTView <GVRVideoViewDelegate>


@property (nonatomic, assign) float volume;
@property (nonatomic, assign) NSDictionary* src;
@property (nonatomic, assign) NSString* displayMode;
@property (nonatomic, assign) BOOL enableFullscreenButton;
@property (nonatomic, assign) BOOL enableCardboardButton;
@property (nonatomic, assign) BOOL enableInfoButton;
@property (nonatomic, assign) BOOL hidesTransitionView;
@property (nonatomic, assign) BOOL enableTouchTracking;
@property (nonatomic, copy) RCTBubblingEventBlock didLoadContent;

@property (nonatomic, copy) RCTBubblingEventBlock onLoadVideoSuccess;
@property (nonatomic, copy) RCTBubblingEventBlock onLoadVideoFailed;

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher NS_DESIGNATED_INITIALIZER;

@end
