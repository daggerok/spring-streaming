import './styles'

import React, {Component} from 'react'
import {render} from 'react-dom'
import {Router, browserHistory, Route, IndexRoute} from 'react-router'

import Nav    from './components/Nav'
import Links  from './components/Links'
import Footer from './components/Footer'
import Index  from './components/Index'
import Admin  from './admin/components/Admin'

class App extends Component {
  render() {
    return (
      <div class="container-fluid">
        <Nav>
          <Links />
        </Nav>

        <div class="container-fluid">
          {/* this will be either <Index> or <Admin> */}
          {this.props.children}
        </div>

        <Footer />
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
