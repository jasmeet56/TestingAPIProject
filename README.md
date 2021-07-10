# Coding Test

## Requirements

For building and running the application you need:

- [JDK 8.0]
- [Dev Tools Plugin]
- [Lombok Plugin in IDE] 

I've used Intellij IDEA for developing and executing the application.

## Running the application locally

Execute/Run the `main` method in the `TestingApiProjectApplication` class in package `com.testing.assignemnt` from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## EndPoints

## NOTE : 

Requirement :Testing API's exporting features




Once the application is up and tomcat is running in localhost(9100) hit the below URIs:


Endpoint for return data as JSON Response:

http://localhost:9100/api/v1/organization/data/

Endpoint for exporting data in csv format: http://localhost:9100/api/v1/organization/data/export

Endpoint for retrieving data when in ascending order by organization : http://localhost:9100/api/v1/organization/data/sortByAsc?inputParam=organization

Endpoint for retrieving data when in ascending order by releaseCount : http://localhost:9100/api/v1/organization/data/sortByAsc?inputParam=releaseCount

Endpoint for retrieving data when in ascending order by labourHours : http://localhost:9100/api/v1/organization/data/sortByAsc?inputParam=labourHours

Endpoint for retrieving data when in descending order by organization : http://localhost:9100/api/v1/organization/data/sortByDesc?inputParam=organization

Endpoint for retrieving data when in descending order by releaseCount : http://localhost:9100/api/v1/organization/data/sortByDesc?inputParam=releaseCount

Endpoint for retrieving data when in descending order by labourHours : http://localhost:9100/api/v1/organization/data/sortByDesc?inputParam=labourHours