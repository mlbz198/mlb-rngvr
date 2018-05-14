/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react'
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native'
import { VideoView, PanoramaView } from 'mlb-rngvr'

export default class App extends Component {
  render () {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <VideoView
          style={{ height: 300, width: 200 }}
          source={{
            uri: 'https://raw.githubusercontent.com/googlevr/gvr-ios-sdk/master/Samples/VideoWidgetDemo/resources/congo.mp4',
            type: 'mono'
          }}
          displayMode={'embedded'}
          volume={1}
          paused={false}
          enableFullscreenButton
          enableCardboardButton
          enableTouchTracking
          hidesTransitionView
          enableInfoButton={false}
        />
        {/* <PanoramaView
          style={{ height: 300, width: 200, backgroundColor: '#ccc' }}
          source={{ uri: 'https://roadtovrlive-5ea0.kxcdn.com/wp-content/uploads/2014/09/Venice.Still001.jpeg' }}
        /> */}
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5
  }
})
