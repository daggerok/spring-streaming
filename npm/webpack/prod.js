'use strict';

let
  webpack = require('webpack'),
  config  = require('./dev'),
  banner  = `
your multi line company banner is here...

2016 (c) Maksim Kostromin
`;

config.devtool = '#cheap-source-map';
config.plugins = [
  ...config.plugins,
  new webpack.optimize.DedupePlugin(),
  new webpack.optimize.OccurenceOrderPlugin(),
  /*new webpack.optimize.UglifyJsPlugin({
    sourcemap: false,
    compress: {
      warnings: false
    },
    mangle: {
      except: [
        '$super',
        '$',
        'exports',
        'require'
      ]
    }
  }),*/
  new webpack.BannerPlugin(banner),
];

module.exports = config;
