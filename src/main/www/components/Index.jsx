import React, {Component} from 'react'
import TicTacToe from './Index/TicTacToe'
import SockJS from '../sockjs'
import Stomp from 'stompjs/lib/stomp'

export default class Index extends Component {
  render() {
    console.log('SockJS', SockJS)
    console.log('Stomp', Stomp)
    return (
      <div class="container-fluid">
        <p>some tic-tac and sse-interval esting</p>
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
