#import <Foundation/Foundation.h>
#import "VrVideoManager.h"
#import <React/RCTBridge.h>
#import "VrVideoView.h"
#import <UIKit/UIKit.h>

@implementation VrVideoManager

RCT_EXPORT_MODULE();

@synthesize bridge = _bridge;

- (UIView *)view
{
  return [[VrVideoView alloc] initWithEventDispatcher:self.bridge.eventDispatcher];
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_VIEW_PROPERTY(enableFullscreenButton, BOOL);
RCT_EXPORT_VIEW_PROPERTY(enableCardboardButton, BOOL);
RCT_EXPORT_VIEW_PROPERTY(enableTouchTracking, BOOL);
RCT_EXPORT_VIEW_PROPERTY(hidesTransitionView, BOOL);
RCT_EXPORT_VIEW_PROPERTY(enableInfoButton, BOOL);
RCT_EXPORT_VIEW_PROPERTY(displayMode, NSString);
RCT_EXPORT_VIEW_PROPERTY(src, NSDictionary);
RCT_EXPORT_VIEW_PROPERTY(volume, float)
RCT_EXPORT_VIEW_PROPERTY(onLoadVideoSuccess, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onLoadVideoFailed, RCTBubblingEventBlock)

- (NSArray *) customDirectEventTypes {
  return @[
           @"onDidloadVideo"
          ];
}

@end
