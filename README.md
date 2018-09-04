# spring-streaming [![Build Status](https://travis-ci.org/daggerok/spring-streaming.svg?branch=master)](https://travis-ci.org/daggerok/spring-streaming)

This example gradle multi-project showing Full-stack app using simplest Spring Boot 1.x + Reactor event-bus, websockets and React frontend apps:

- backend:
  - spring boot 1.5.15.RELEASE
  - spring-mvc
  - spring-security
  - spring-messaging
  - spring-data-rest
  - spring-hateoas

- frontend
  - bootstrap
  - babel ES6
  - React:
    - JSX
    - react-router

- build:
  - gradle
  - node
  - npm
  - webpack
  - spring-boot-devtools (live reload)

npm is required

**Please take a note: I'm not going to support that crap anymore... JS libraries absolutely incompatible and non backward compatible during some time... Sometimes in a past it worked.**

production mode:

```shell
gradle clean build
java -jar build/libs/*.jar
```

development mode:

```shell
npm start
gradle bootRun
open http://localhost:8080
```

open page, turn on livereload browser extension, in idea change code and press alt+command+y, command+f9
this will rebuild your java/javascript sources and trigger spring-boot-devtools restart/reload handler

testing Ssemmiter
```shell
curl -iv localhost:8080/test/sse-interval # or open these URLs in the browser
curl -iv localhost:8080/test/sse-interval-with-status
curl -iv http://localhost:8080/test/streaming-response-body
```

### in addition

outdated dependencies:

```shell
gradle dependencyUpdates
...
The following dependencies have later release versions:
 - org.springframework.boot:spring-boot-gradle-plugin [1.3.2.RELEASE -> 1.3.3.RELEASE]

npm outdated
npm update -D # or gradle npm -Ptask=update
```

read more:

- [spring](https://spring.io)
- [spring-gradle dependency management plugin](https://github.com/spring-gradle-plugins/dependency-management-plugin)
- [gradle versions plugin](https://github.com/ben-manes/gradle-versions-plugin)
- [webpack production optimizations](https://github.com/webpack/docs/wiki/code-splitting)
- [webpack plugins](https://github.com/webpack/docs/wiki/list-of-plugins)
- [React](https://facebook.github.io/react/docs/getting-started.html)
- [react-router](https://github.com/reactjs/react-router)
- [react-active-component](https://github.com/insin/react-router-active-component)
