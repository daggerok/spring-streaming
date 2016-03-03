'use strict';

let
  config  = require('./webpack.config'),
  webpack = require('webpack');

config.devtool = null;
config.plugins = [
  ...config.plugins,
  new webpack.optimize.DedupePlugin(),
  new webpack.optimize.OccurenceOrderPlugin(),
  new webpack.optimize.UglifyJsPlugin({
    sourcemap: false,
    compress: {
      warnings: false
    },
    mangle: {
      except: ['$super', '$', 'exports', 'require']
    },
  }),
];

module.exports = config;
