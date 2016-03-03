'use strict'

let
  config  = require('./webpack.config'),
  webpack = require('webpack'),
  banner  = `
your multiline company banner is here...
2016 (c) Maksim Kostromin
`

config.devtool = null;
config.plugins = [
  ...config.plugins,
  new webpack.optimize.DedupePlugin(),
  new webpack.optimize.OccurenceOrderPlugin(),
  new webpack.optimize.UglifyJsPlugin({
    sourcemap: false,
    compress: {
      warnings: false,
    },
    mangle: {
      except: [
        '$super',
        '$',
        'exports',
        'require',
      ]
    },
  }),
  new webpack.BannerPlugin(banner),
]

module.exports = config;
