#!/bin/bash

cd ./Pods/GVRSDK/Libraries/
lipo -info libGVRSDK.a

# Divide to each platform
lipo -thin armv7 libGVRSDK.a -output libGVRSDK_armv7
lipo -thin i386 libGVRSDK.a -output libGVRSDK_i386
lipo -thin x86_64 libGVRSDK.a -output libGVRSDK_x86_64
lipo -thin arm64 libGVRSDK.a -output libGVRSDK_arm64

# Delete duplicate file
chmod 777 libGVRSDK_armv7
chmod 777 libGVRSDK_i386
chmod 777 libGVRSDK_x86_64
chmod 777 libGVRSDK_arm64

ar -dv libGVRSDK_armv7 vlog_is_on.o
ar -dv libGVRSDK_i386 vlog_is_on.o
ar -dv libGVRSDK_x86_64 vlog_is_on.o
ar -dv libGVRSDK_arm64 vlog_is_on.o

# rm libGVRSDK.a
lipo -create libGVRSDK_armv7 libGVRSDK_i386 libGVRSDK_x86_64 libGVRSDK_arm64 -output libGVRSDK.a

# Delete media
rm libGVRSDK_armv7
rm libGVRSDK_i386
rm libGVRSDK_x86_64
rm libGVRSDK_arm64

cd ../../../