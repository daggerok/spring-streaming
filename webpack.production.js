'use strict';

const
    config  = require('./webpack.config'),
    webpack = require('webpack'),
    debug   = true;

config.devtool = null;
config.plugins = [
    new webpack.optimize.DedupePlugin(),
    new webpack.optimize.OccurenceOrderPlugin(),
    new webpack.optimize.UglifyJsPlugin({
        mangle: false,
        sourcemap: false,
        compress: true
    })
];

module.exports = config;
