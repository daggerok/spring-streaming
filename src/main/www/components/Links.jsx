import React, {Component} from 'react'
import {Link} from 'react-router'

export default class Links extends Component {
  render() {
    return (
      <ul class="nav navbar-nav">
        <li class="active">
          <Link to="/" class="active">Go home</Link>
        </li>
        <li>
          <Link to="admin">Show me Admin</Link>
        </li>
      </ul>
    )
  }
}
