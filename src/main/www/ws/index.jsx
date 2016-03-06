import SockJS from './sockjs/sockjs'
import {Stomp} from 'stompjs/lib/stomp'
const defaultEndpoint = '/messaging'

export default class WS {
  constructor(endpoint) {
    this._endpoint = endpoint? endpoint : '/messaging'
    this._subscriptions = new Set()
  }

  connect(callback) {
    this.socket = new SockJS(this.endpoint)
    this.stompClient = Stomp.over(this.socket)

    this.stompClient.connect({}, callback)
  }

  disconnect(callback) {
    this.stompClient.disconnect(() => console.log('see ya!'))
  }

  subscribeAll(configs) {
    configs.forEach(config => this.subscribe(config))
  }

  subscribe(config) {
    this.connect(frame => {  
      let subscription = this.stompClient.subscribe(config.route, config.callback)
      console.log(`subscribed new config: ${JSON.stringify(subscription)}`)
      this.subscriptions.add(subscription.id)
      return subscription.id
    })
  }

  unsubscribe(id) {
    if (this.subscriptions.has(id)) {
      this.stompClient.unsubscribe(id)
      this.subscriptions.delete(id)
    } else {
      console.log(`subscription with ${id} wasn't found.`)
    }
  }

  unsubscribeAll() {
    this.subscriptions.forEach(subscription => this.unsubscribe(subscription.id))
  }

  set endpoint(endpoint) {
    return this._endpoint = endpoint
  }

  get endpoint() {
    return this._endpoint
  }

  set socket(socket) {
    return this._socket = socket
  }

  get socket() {
    return this._socket
  }

  set stompClient(stompClient) {
    return this._stompClient = stompClient
  }

  get stompClient() {
    return this._stompClient
  }

  get subscriptions() {
    return this._subscriptions
  }
}
