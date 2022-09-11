# What is this?

Ich versuche anhand einiger noch zu entwickelnder Usecases für das Game PathOfExile, verschiedene mir neue Technologien
und Konzepte zu erlernen.

- Reactive Programming
- React
- Kubernetes
- Github Pipelines
- ~~Gradle~~ // because fuck gradle
- MapStruct

## Links

- Tests von reactive Streams
    - https://www.baeldung.com/reactive-streams-step-verifier-test-publisher
    - https://dzone.com/articles/spring-5-web-reactive-flux-and-mono
    - https://medium.com/swlh/stepverifier-vs-block-in-reactor-ca754b12846b
- YT Tutorial zu grundlegenden Themen um Reactive Development
    - https://www.youtube.com/watch?v=FbM_CfCHugE&list=PLnXn1AViWyL70R5GuXt_nIDZytYBnvBdd
- Reactive Springboot Starter
    - https://github.com/Brajendra/springboot-reactive-starter-kit
- Building Reactive Backend App
    - https://medium.com/@BPandey/building-reactive-backend-app-with-spring-boot-webflux-fc6610e4a747
- Unit Tests in einer Reactive Backend App
    - https://medium.com/@BPandey/writing-unit-test-in-reactive-spring-boot-application-32b8878e2f57
- Mini Tutorial (1 Page) - MongoDB Reactive Access
    - https://zetcode.com/springboot/mongodbreactive/
- Matt Raible - Boot, Webflux
    - https://developer.okta.com/blog/2018/09/24/reactive-apis-with-spring-webflux
    - https://developer.okta.com/blog/2018/09/25/spring-webflux-websockets-react
- Okta React + Webflux
    - https://github.com/oktadev/okta-spring-webflux-react-example
- React with Typescript Support
    - https://create-react-app.dev/docs/adding-typescript/
    - https://github.com/typescript-cheatsheets/react#reacttypescript-cheatsheets
- Typescript
    - https://ts.chibicode.com/todo/
    - https://2ality.com/2018/04/type-notation-typescript.html

# Entwicklungsentscheidungen:

- kein ReactiveMongoTemplate, sondern ReactiveMongoRepository Ansatz -> war von der API her verständlicher, Unterschied
  noch unklar
-

# Personal Notes

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

# Fun Facts

## Fun Fact 1

Wenn man einen FunktionalInterface hat, das einen Parameter vorsieht für den Funktionsaufruf,

    @FunctionalInterface
    public interface SampleFunction<T extends Object> {
      Result handle(Object request); // Parameter Object
    }

man diesen aber in der eigentlichen Methode nicht benötigt

    callFunctionalInterface(SomeObject::doIt)
    ...
    public Result doIt(Object request) {
      return new Result();  // <- Parameter request nicht benutzt
    }

Dann meckert Sonar/IntelliJ an, das der Parameter überflüssig ist, da der Zusammenhang zum
Functional Interface nicht hergestellt wird.
Das kann man umgehen, indem man den Parameter mit dem Suffix "ignored" versieht.

    public Result doIt(Object ignoredRequest) // <- valide

}