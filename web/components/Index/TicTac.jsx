import {Component} from 'react'
import $ from 'jquery'

export default class TicTac extends Component {
  constructor(props) {
    super(props)
    this.state = { data: '' }
    this.ajax = this.ajax.bind(this)
  }

  ajax() {
    $.get(this.props.url).then(data => {
      console.log('data', data)
      this.setState({data})
    })
  }

  render() {
    return (
      <div class="col-sm-3">
        <h3 class="panel panel-heading">{this.props.title}</h3>
        <button onClick={this.ajax} type="button" class="btn btn-default">click me</button>
        <div class="pre-scrollable max-height-600px">
          {this.state.data}
        </div>
      </div>
    )
  }
}
