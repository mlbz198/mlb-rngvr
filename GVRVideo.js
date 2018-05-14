/*
 * @Author: tiero
 * @Date: 2017-01-05 17:39:15
 * @Last Modified by: tiero
 * @Last Modified time: 2017-01-05 17:40:04
 */
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { requireNativeComponent, ViewPropTypes } from 'react-native'
import resolveAssetSource from 'react-native/Libraries/Image/resolveAssetSource'

class VideoView extends Component {
  render () {
    const source = resolveAssetSource(this.props.source) || {}
    let uri = source.uri || ''
    if (uri && uri.match(/^\//)) {
      uri = `file://${uri}`
    }

    const isNetwork = !!(uri && uri.match(/^https?:/))
    const isAsset = !!(uri && uri.match(/^(assets-library|content|ms-appx|ms-appdata):/))

    return <RCTViedoView
      {...this.props}
      src={{
        uri,
        isNetwork,
        isAsset,
        type: source.type || ''
      }}
    />
  }
}

VideoView.propTypes = {
  ...ViewPropTypes,
  src: PropTypes.object,
  source: PropTypes.oneOfType([
    PropTypes.shape({
      uri: PropTypes.string,
      type: PropTypes.string
    }),
    // Opaque type returned by require('./video.mp4')
    PropTypes.number
  ]),
  videoType: PropTypes.string,
  volume: PropTypes.number,
  displayMode: PropTypes.string,
  enableFullscreenButton: PropTypes.bool,
  enableCardboardButton: PropTypes.bool,
  enableInfoButton: PropTypes.bool,
  enableTouchTracking: PropTypes.bool,
  hidesTransitionView: PropTypes.bool
}

// requireNativeComponent automatically resolves this to "VideoManager"
var RCTViedoView = requireNativeComponent('VrVideo', VideoView)
export default VideoView
