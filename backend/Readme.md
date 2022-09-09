# What is this?

Ich versuche anhand einiger noch zu entwickelnder Usecases für das PathOfExile Game, verschiedene Technologien und
Konzepte zu erlernen.

- Reactive Programming
- React
- Kubernetes
- Github Pipelines
- Gradle

## Links

- Tests von reactive Streams
    - https://www.baeldung.com/reactive-streams-step-verifier-test-publisher
    - https://dzone.com/articles/spring-5-web-reactive-flux-and-mono
    - https://medium.com/swlh/stepverifier-vs-block-in-reactor-ca754b12846b
- YT Tutorial zu grundlegenden Themen
    - https://www.youtube.com/watch?v=FbM_CfCHugE&list=PLnXn1AViWyL70R5GuXt_nIDZytYBnvBdd&index=4
- Reactive Springboot Starter
    - https://github.com/Brajendra/springboot-reactive-starter-kit
- Building Reactive Backend App
    - https://medium.com/@BPandey/building-reactive-backend-app-with-spring-boot-webflux-fc6610e4a747
- Unit Tests in einer Reactive Backend App
    - https://medium.com/@BPandey/writing-unit-test-in-reactive-spring-boot-application-32b8878e2f57
- Mini Tutorial (1 Page)
    - https://zetcode.com/springboot/mongodbreactive/

Entscheidungen:

- kein ReactiveMongoTemplate, sondern ReactiveMongoRepository Ansatz -> war von der API her verständlicher, Unterschied
  noch unklar
-

# Self Doku

## Reactive Programming

- APIs
    - Reactor (Pivotal)
    - Java 9 (Flow)
    - RxJava

### Publisher

    - bietet subscribe Methode mit Subscriber Param
    - usually Data Producers

### Subscriber

    - onSubscribe - 
    - onNext - nächstes Element aus dem Datastream
    - onError - Fehler im Datastream
    - onComplete - Datastream beendet

### Subscription

    - request - anfordern von (n) Datenpaketen
    - cancel - beenden der Subscription 

### Processor

    - Kombination aus Publisher und Subscriber

### Reactive Types von Project Reactor

#### Flux

    - Ist ein Publisher
    - repräsentiert 0 bis n Elemente

#### Mono

    - Ist ein Publisher
    - repräsentiert 0 bis 1 Element
