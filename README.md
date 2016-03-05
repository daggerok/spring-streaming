spring-streaming [![build] (https://travis-ci.org/daggerok/spring-streaming.svg?branch=master)] (https://travis-ci.org/daggerok/spring-streaming) 
================

SPA boilerplate on Spring Boot with WebSockets and React (in progress...)

full stack:

- gradle, node, npm, webpack

- spring:
  - spring boot
  - spring-mvc
  - spring-security
  - spring-messaging
  - spring-data-rest
  - spring-hateoas

- babel ES6, React, JSX

npm required:

production mode:

```bash
npm run build
gradle clean build
java -jar build/libs/*.jar
```

development mode:

```bash
npm start
gradle bootRun
```

outdated dependencies:

```shell
gradle dependencyUpdates
...
The following dependencies have later release versions:
 - org.springframework.boot:spring-boot-gradle-plugin [1.3.2.RELEASE -> 1.3.3.RELEASE]

npm outdated
npm update -D
```

read more:

- [webpack production optimizations ] (https://github.com/webpack/docs/wiki/code-splitting)

- [webpack plugins ] (https://github.com/webpack/docs/wiki/list-of-plugins)

- [gradle versions plugin] (https://github.com/ben-manes/gradle-versions-plugin)
