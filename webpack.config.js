'use strict';

const
    debug   = true,
    path    = require('path'),
    exclude = /(node_modules|bower_components)/,
    mainDir = `${__dirname}/src/main`;

module.exports = {
    context: __dirname,
    devtool: debug ? 'cheap-inline-module-source-map' : null,
    entry: `${mainDir}/www/main`,
    output: {
        path: `${mainDir}/resources/static/`,
        filename: 'bundle.js'
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    },
    module: {
        loaders: [
            {test: /\.(js|jsx)$/, exclude, loader: 'babel-loader'},
            {test: /\.css$/, loader: 'style-loader!css-loader'},
            {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file-loader'},
            {test: /\.(woff|woff2)$/, loader: 'url?prefix=font/&limit=5000'},
            {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=image/svg+xml'},
            {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream'}
        ]
    }
};
