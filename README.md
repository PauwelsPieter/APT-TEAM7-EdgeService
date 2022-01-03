# APT-TEAM7-EdgeService

- [Introduction](#introduction)
- [Microservices](#microservices)
- [Diagram](#diagram)
- [Postman](#postman)
- [Swagger](#swagger)
- [Unit and Integration tests](#unit-and-integration-tests)
- [Front-end](#front-end)
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
![Diagram](https://user-images.githubusercontent.com/57799581/147971704-b32e518a-f0f4-4b70-add1-bbeb8d767d3e.png)

## Postman
For testing the endpoints we can meke use of Postman. At [this](https://documenter.getpostman.com/view/18957198/UVRHiPPz) link you can find the documentation for each endpoint of the edge service. You can also test these endpoints by clicking "Run in Postman" and then running the collection.
![postmanTests](https://user-images.githubusercontent.com/57799581/147872942-e54c77a4-ae2b-4570-84ca-a946e91a3f70.png)


## Swagger
Making use of SwaggerUI we document the endpoints that can be accessed on the [edge service](https://edge-service-server-pauwelspieter.cloud.okteto.net/swagger-ui.html).

![swaggerListOperations](https://user-images.githubusercontent.com/57799581/147970788-08aa47d5-d084-48f6-9f06-d23b61cc1992.PNG)
![swaggerExpandOperations](https://user-images.githubusercontent.com/57799581/147971126-ce6680eb-970a-426c-aca3-e9a963153180.png)

## Unit and Integration tests
For each 'back-end' microservice we added unit and integration tests. For the edge microservice we added unit tests, no integration tests because this service doesn't make use of a database but instead uses the 'back-end' services to connect to the database.

All the tests cover 100% of the methods for the controllers, repositories and constructors of the model classes.
### Edge service
![codeCoverageEdgeService](https://user-images.githubusercontent.com/57799581/147972376-39718300-2098-49a5-b20a-10bbad06fd07.PNG)

### Brand service
![codeCoverageBrandService](https://user-images.githubusercontent.com/57799581/147872448-1f8f088d-c7de-4206-b28f-d441f302166e.PNG)

### Model service
![codeCoverageModelService](https://user-images.githubusercontent.com/57799581/147972901-0f4f5576-3595-4134-8ea3-ea9c5b1157a8.PNG)

## Front-end
In [this repository](https://github.com/PauwelsPieter/APT-TEAM7-FrontEnd) you can find our basic front-end application that communicates with the edge service.
The front-end can be visited here: https://apt-team7.netlify.app/

The front-end is tested using Selenium in combination with Python. The code for this can be found in [this repository](https://github.com/PauwelsPieter/APT-TEAM7-FrontEndSeleniumTesting).
A demo of the Selenium testing can be found here: https://youtu.be/ujiZQOHegYE

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
- [x] Efficient use of @PathVariable vs. @RequestParam.

- [x] Unit tests for all microservice controllers.
- [x] Integration tests for all microservice controllers.
- [x] 100% method test code coverage for controllers, repositories and constructors of model classes.

- [x] Each GitHub repository has a CI/CD pipeline that runs tests, uploads the .jar as artifact and pushes a Docker container to DockerHub, inclusive SonarCloud.
- [x] Deployment in K8S @ Oktato Cloud.
### Extra
- [x] Making use of K8S secrets for environment variables at deployment. (+5%)
- [ ] Keycloak integration. (+15%)
- [ ] Minimum 1 message queue with for example ActiveMQ. (+15%)
  - [ ] Prometheus auto-scaling via an extra endpoint. (+15%) 
- [x] Basic front-end that communicates with the edge-service. (+15%)
  - [x] Selenium testing of the front-end (+15%)
