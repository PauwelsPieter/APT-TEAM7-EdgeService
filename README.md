# APT-TEAM7-EdgeService

- [Introduction](#introduction)
- [Microservices](#microservices)
- [Diagram](#diagram)
- [Postman](#postman)
- [Swagger](#swagger)
- [Unit and Integration tests](#unit-and-integration-tests)
- [Requirements](#requirements)
  - [General](#general)
  - [Extra](#extra)

## Introduction
Welcome :wave: to our project for the Advanced Programming Topics course. Our team :busts_in_silhouette: consists out of Jorne Marx and Pieter Pauwels.

For this project we created a microservice architecture that contains 3 microservices. 

Our topic is based around car :car: brands and different models that these brands contain. Our brand microservice makes use of MongoDB and our model microservice makes use of PostgreSQL.

## Microservices
- [APT-TEAM7-CarBrandService](https://github.com/PauwelsPieter/APT-TEAM7-CarBrandService)
- [APT-TEAM7-CarModelService](https://github.com/PauwelsPieter/APT-TEAM7-CarModelService)
- [APT-TEAM7-EdgeService](https://github.com/PauwelsPieter/APT-TEAM7-EdgeService)

## Diagram
![Diagram](https://lh3.googleusercontent.com/pw/AM-JKLVjswOS8cmJrVqrTHQtwmH7cOhYZGM5oOIQhRAr0dQtmSZPvYT7X_c-Gqx_rTuVhU9i7KwxmK3_E8OAycx9hPbjBCUUhb8d35fNzZ9RVP4ifcYdB_zinbaekQ31yFqZK1oSU4RUCkgWBWr-K2pzNXpj=w1019-h937-no?authuser=0)

## Postman
For testing the endpoints we can meke use of Postman. At [this](https://documenter.getpostman.com/view/18957198/UVRHiPPz) link you can find the documentation for each endpoint of the edge service. You can also test these endpoints by clicking "Run in Postman" and then running the collection.
![postmanTests]()![postmanTests](https://user-images.githubusercontent.com/57799581/147872942-e54c77a4-ae2b-4570-84ca-a946e91a3f70.png)


## Swagger
Making use of SwaggerUI we document the endpoints that can be accessed on the [edge service](https://edge-service-server-pauwelspieter.cloud.okteto.net/swagger-ui.html).

![swaggerListOperations](https://user-images.githubusercontent.com/57799581/147872073-c86860ca-485c-4f71-8bfd-7b0461efcc66.png)
![swaggerExpandOperations](https://user-images.githubusercontent.com/57799581/147872329-c4a0f6fd-4523-4e43-8f75-01937cb81ffc.png)

## Unit and Integration tests
For each 'back-end' microservice we added unit and integration tests. For the edge microservice we added unit tests, no integration tests because this service doesn't make use of a database but instead uses the 'back-end' services to connect to the database.

All the tests cover 100% of the methods for the controllers, repositories and constructors of the model classes.
### Edge service
![codeCoverageEdgeService](https://user-images.githubusercontent.com/57799581/147872444-cfdecfc1-41b8-43bd-95ab-b5b52a38b543.PNG)

### Brand service
![codeCoverageBrandService](https://user-images.githubusercontent.com/57799581/147872448-1f8f088d-c7de-4206-b28f-d441f302166e.PNG)

### Model service
![codeCoverageModelService](https://user-images.githubusercontent.com/57799581/147938212-68cd9920-8ba3-4806-854b-4dbeafdb40b6.PNG)

## Requirements
### General
- [x] Minimum 2 'Back-end' microservices
- [x] 1 Edge microservice
- [x] Dockerfile for each microservice
- [x] README contains a short description for the chosen topic and a diagram of the complete microservice architecture.
- [x] Demonstrable functionality total achitecture by Postman requests on the Edge microservice.
- [x] Complete implementation SwaggerUI for the Edge microservice and screenshot(s) of the output in the README.

- [x] Minimum 4 GET endpoints on the Edge microservice, never searching on DB id.
- [x] POST, PUT and DELETE endpoints on the Edge microservice.
- [x] Minimum 2 GET endpoints on each 'Back-end'microservice, never searching on DB id.
- [x] POST, PUT and DELETE endpoints for minumum 1 'Back-end' microservice.
- [x] Making use of MongoDB and a no-MySQL DB.
- [ ] Efficient use of @PathVariable vs. @RequestParam.

- [ ] Unit tests for all microservice controllers.
- [ ] Integration tests for all microservice controllers.
- [ ] 100% method test code coverage for controllers, repositories and constructors of model classes.

- [x] Each GitHub repository has a CI/CD pipeline that runs tests, uploads the .jar as artifact and pushes a Docker container to DockerHub, inclusive SonarCloud.
- [x] Deployment in K8S @ Oktato Cloud.
### Extra
- [x] Making use of K8S secrets for environment variables at deployment. (+5%)
- [ ] Keycloak integration. (+15%)
- [ ] Minimum 1 message queue with for example ActiveMQ. (+15%)
  - [ ] Prometheus auto-scaling via an extra endpoint. (+15%) 
- [ ] Basic front-end that communicates with the edge-service. (+15%)
  - [ ] Selenium testing of the front-end (+15%)
