
#  A modern and secure rental cars application based on Microservices Architecture 
## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://www.linkedin.com/in/ismail-harik-241b371b9/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ismail-harik-241b371b9)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/ismail_harik)



This project is designed to allow users to book and manage their car rentals through a rental car application. The application is built using a microservices architecture and incorporates several tools and technologies of Spring Cloud for the backend and Angular 13 for the frontend. The main objective of this project is to help developers become more familiar with Microservices Architecture and to provide them with easy access to the most important configurations required to build microservices-based applications using Spring Cloud , I have dockerized all services , with one you can run all application.

![img.png](img.png)

## Rental Cars Services:
- [x] cars-services
- [x] notification-service
- [x] customers-service
- [x] orders-service
- [x] offices-service



## tools & techs

#### Routing All Microservices Requests :
* Spring cloud Gateway
#### Service Discovery :
* Consul Server
#### Secret Management :
* Vault Server

####  Inter Service Communication :
*  openfeign (sync Communication)
*  web clients (sync / async Communication)

#### Resilient Communication :
* Circuit Breaker
* Resilience4J

#### Distributed Tracing :
* Micrometer Tracing & Zipkin
  * Tracing — seeing WHAT happened
  * Metrics — seeing HOW LONG it took to happen

#### unit & integration test (orders-service) :
* JUnit
* Mockito

#### storing and serving distributed configurations across all service :
* Spring Cloud Config

#### Event streaming
* Kafka Broker
#### Zero Trust Security Architecture :
* Spring Security
* Keycloak (Open Source Identity Manager And Access Management) covered two clients :
  1.  rental-cars-clients : for backend to (authenticate you will need to provide symetric key + user credentials)
  2. rental-cars-front :  you will need only user credentials as it isn't secure to store symetric key in the front (people can access it from js )
#### Monitoring
* Prometheus & Grafana
####  Databases :
* MySQL MaeriaDB
* MongoDB
* PostgresDB
* Oracle ( if you have installed in your host machine)
#### Front End (two app , one for clients & the other for admin):
* Angular 13
* Bootstrap 5
* HightCharts to visualize admine data
* Admin app secured with keycloak

#### containerization
* Docker
* Docker-Compose



## Usage :

### To build docker images ,make sure to replace to each service directory and  run for each one this commands :
<p>First clone projects  </p>

```bash

[//]: # (Admin dashboard)
git clone https://github.com/Ismailharik/rental-cars-admin.git

[//]: # (Clients interface )
git clone https://github.com/Ismailharik/rental-cars-front.git

[//]: # (backend services)
git clone https://github.com/Ismailharik/rental-cars.git
```
<p> Build Image for your backend service or </p>

```bash
docker build -t orders-service .
docker build -t gateway-service .
docker build -t customers-service .
docker build -t notification-service .
docker build -t offices-service .
docker build -t orders-service .
docker build -t config-service .
docker build -t consul-config-service . 
# then you can run the cmd below to run all application
docker-compose up
```
#### To test the endpoints from postman 

##### make sure to add on the request header this authorization : ![img_8.png](img_8.png)
  <span>If you want to test backend services with rental-cars-client , make sure to add client secret </span>
  * To run them without docker you will need to  update host name of keycloak to localhost :
  * inside postman ![img_9.png](img_9.png)
  * admin dashboard  to localhost : ![img_4.png](img_4.png)
  * late DNS know keycloak hostname , got to  hosts file : ![img_3.png](img_3.png)

  

### run


### All-Ports
<p>Services  Ports : </p>


| service               | Ports |
|-----------------------|-------|
| orders-service        | 8081  |
| cars-service          | 8082  |
| customers-service     | 8083  |
| offices-service       | 8084  |
| consul-config-service | 8085  |
| notification-service  | 8086  |
| config-service        | 8888  |
| gateway-service       | 9999  |
| client interface      | 4201  |
| admin dashboard       | 4200  |


<p>Tools  Ports : </p>

| service               |Ports|
|-----------------------| ---|
| zipkin-server         |9411|
| vault-server          |8200|
| keycloak-service      |8080|
| promethues            |9090|
| grafana               |3000|
| zookeeper             |2181|
| kafka                 |9092|








### Change offices db from Postgres to Oracle

If you have installed oracle db in your machine and want to use it make sure to uncomment it's dependency & config from offices service & comment postgres :
![oracleConfig](https://user-images.githubusercontent.com/92827404/215745836-5a37ac13-b2e1-49dd-9948-b49189fed5f2.png)





### related projects :

<a href="https://github.com/SaiUpadhyayula/spring-boot-microservices-new">SaiUpadhyayula/spring-boot-microservices-new</a> <br>
<a href="https://github.com/Aliot26/microservices_v3">Aliot26/microservices_v3</a> <br>
<a href="https://github.com/in28minutes/spring-microservices">in28minutes/spring-microservices</a>

