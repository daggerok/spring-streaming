import './styles'

import React, {Component} from 'react'
import {render} from 'react-dom'
import {Router, browserHistory, Route, IndexRoute, Link} from 'react-router'

import Index from './components/Index'
import Admin from './admin/components/Admin'

export default class App extends Component {
  render() {
    return (
        <div>
          this is an App
          {/* this will be either <Index> or <Admin> */}
          {this.props.children}
          <Link to="admin" class="btn btn-default">Show me admin</Link><br/>
          <Link to="/" class="btn btn-default">Go home</Link><br/>
        </div>
    )
  }
}

render(
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={Index} />
      <Route path="admin" component={Admin} />
    </Route>
  </Router>,
  document.getElementById('app')
)
