'use strict';

let
  context = `${__dirname}/../..`,
  devtool = 'cheap-inline-module-source-map',
  mainDir = `${context}/src/main`,
  path = `${mainDir}/resources/public/`,
  exclude = /(node_modules|bower_components)/,
  webpack = require('webpack'),
  definePlugin = webpack.DefinePlugin({
    __DEV__: JSON.stringify(JSON.parse(process.env.BUILD_DEV || 'true')),
    __PROD__: JSON.stringify(JSON.parse(process.env.BUILD_PROD || 'false'))
  })

/*
  // in the code:
  if (__DEV__) {
    console.warn('some extra debug logging');
  }
  if (__PROD__) {
    helpdesk.notify('send crash report');
  }
*/

module.exports = {
  context,
  devtool,
  entry: {
    vendors: [
      'react',
      'react-dom',
      'bootstrap/dist/css/bootstrap.min.css',
    ],
    app: `${mainDir}/www/main`,
    admin: `${mainDir}/www/admin/main`
  },
  output: {
    path,
    filename: '[name].js'
    // used to generate URLs to e.g. images
    //publicPath: 'http://mycdn.com/'
  },
  resolve: {
    extensions: ['', '.json', '.js', '.jsx']
  },
  module: {
    loaders: [
      {test: /\.(js|jsx)$/, exclude, loader: 'babel-loader'},
      // use '!' to chain loaders
      {test: /\.less$/, loader: 'style-loader!css-loader!less-loader'},
      {test: /\.css$/, loader: 'style-loader!css-loader'},
      {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file-loader'},
      {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10240&mimetype=image/svg+xml'},
      {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10240&mimetype=application/octet-stream'},
      {test: /\.(woff|woff2)$/, loader: 'url?prefix=font/&limit=5120'},
      // inline base64 URLs for < 5k images, direct URLs for the rest
      {test: /\.(png|jpg)$/, loader: 'url-loader?limit=5120'}
    ]
  },
  // This will remove all modules in the vendors chunk from the app and admin chunks. (see condig.entry)
  plugins: [
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendors',
      filename: 'vendors.js'
      // with this, you will link only one bundle on a page (app.js or admin.js), within vendors.js inside
      // children: true,
    })
  ]
}
