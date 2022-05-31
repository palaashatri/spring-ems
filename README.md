# ems
Employee Management System built with Spring Boot, Thymeleaf and MySQL

### Requirements
 1. Download & Install Java 11 : [Download](https://www.oracle.com/java/technologies/downloads/)
 2. Download & Install MySQL 8.0.* : [Download](https://dev.mysql.com/downloads/installer/)
 
### Steps
1. Clone this repository : 
```
git clone https://github.com/palaashatri/spring-ems
```
2. Change the following values inside `ems/src/main/resources/application.properties` according to your system configuration:
```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/ems
spring.datasource.username=
spring.datasource.password=
```
3. Inside MySQL, create a database called 'ems':
```
create database ems;
```
4. Run project using Eclipse IDE or through mvnw:
```
./mvnw clean install
```
5. You can find the project running on [localhost:8080](http://localhost:8080)
