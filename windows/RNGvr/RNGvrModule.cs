using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Gvr.RNGvr
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNGvrModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNGvrModule"/>.
        /// </summary>
        internal RNGvrModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNGvr";
            }
        }
    }
}
