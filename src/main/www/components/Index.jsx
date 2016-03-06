import React, {Component} from 'react'
import TicTacToe from './Index/TicTacToe'
import WS from '../ws'

export default class Index extends Component {
  constructor(props) {
    super(props)

    this.state = {
      ws: new WS('/messaging'),
      config: {
        route: '/topic',
        callback: () => console.log('topic cb')
      },
      isSubscribed: false,
      btnLabel: 'subscribe'
    }

    this.subscribe = this.subscribe.bind(this)
  }

  subscribe() {
    this.state.ws.subscribe(this.state.config)
    
    this.setState({ isSubscribed: !this.state.isSubscribed })

    console.log(this.state.ws)
    console.log('ws.socket', this.state.ws.socket)
    console.log('ws.stompClient', this.state.ws.stompClient)
    console.log('ws.endpoint', this.state.ws.subsc)
  }

  render() {
    return (
      <div class="container-fluid">
        <p>some tic-tac and sse-interval esting</p>
        <button onClick={this.subscribe} 
                disabled={this.state.isSubscribed? 'disabled' : ''} 
                type="button" 
                class="btn btn-default">{this.state.btnLabel}</button>
        <TicTacToe />
      </div>
    )
  }
}

/*
import React from 'react'

export default () => (
  <div class="container-fluid">
    <p>Hey, u! holy routing.... this is an index!</p>
  </div>
)
*/
