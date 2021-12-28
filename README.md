# APT-TEAM7-EdgeService

- [Introduction](#introduction)
- [Microservices](#microservices)
- [Diagram](#diagram)
- [Postman](#postman)
- [Swagger](#swagger)
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

## Swagger

## Requirements
### General
- [x] Minimum 2 'Back-end' microservices
- [x] 1 Edge microservice
- [x] Dockerfile for each microservice
- [x] README contains a short description for the chosen topic and a diagram of the complete microservice architecture.
- [ ] Demonstrable functionality total achitecture by Postman requests on the Edge microservice.
- [ ] Complete implementation SwaggerUI for the Edge microservice and screenshot(s) of the output in the README.

- [ ] Minimum 4 GET endpoints on the Edge microservice, never searching on DB id.
- [ ] POST, PUT and DELETE endpoints on the Edge microservice.
- [ ] Minimum 2 GET endpoints on each 'Back-end'microservice, never searching on DB id.
- [ ] POST, PUT and DELETE endpoints for minumum 1 'Back-end' microservice.
- [ ] Making use of MongoDB and a no-MySQL DB.
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
