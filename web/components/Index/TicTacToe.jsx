import {Component} from 'react'
import TicTac from './TicTac'

export default class TicTacToe extends Component {
  constructor(props) {
    super(props)
    this.state = {
      url: {
        ticTac:     '/test/tic-tac',
        ticTacToe:  '/test/tic-tac-toe',
        emitter:  '/test/sse-interval'
      }
    }
  }

  render() {
    return (
      <div class="row">

        <TicTac title='Tic Tac'
                url={this.state.url.ticTac} />

        <TicTac title='Tic Tac Toe'
                url={this.state.url.ticTacToe} />

        <TicTac title='Emitter'
                url={this.state.url.emitter} />

      </div>
    )
  }
}
