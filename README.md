# MSA Ecommerce
- [MSA Ecommerce] Catalog Service Instance

## 프로젝트 개발 구성
- Java 8
- Spring Boot(+Maven) 2.6.4
- Spring Cloud 2021.0.1
  - Eureka-client 3.1.1
  - Config 3.1.1
  - Bus-amqp(RabbitMq) 3.1.1
- H2(Embedded) 1.4.200
- Spring Data JPA 2.6.4
- Junit 5

## 프로젝트 서버 구성
- IP : localhost
- PORT : 8888
- RabbitMq : localhost:5672

## 추가 정리
- **로드밸런싱 테스트를 위한 여러개의 서버 구동방법**
  - Intellij Run
  - Terminal
    1. $ mvnw spring-boot:run
    2. $ mvnw clean compile package  
       $ java -jar ./target/catalog-service-0.0.1-SNAPSHOT.jar
