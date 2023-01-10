# Getting Started

prepare docker network
```shell
docker network create catalog-network
```

build jar
```shell
./gradlew bootJar
```
build docker image
```shell
docker build -t catalog-service .
```
prepare docker DB container
```shell
$ docker run -d --name polar-postgres --net catalog-network -e POSTGRES_USER=user -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=polardb_catalog -p 5432:5432 postgres:14.4
```
run container catalog-service in catalog-network
```shell
$ docker run -d --name catalog-service --net catalog-network -p 9001:9001 -e SPRING_DATASOURCE_URL=jdbc:postgresql://polar-postgres:5432/polardb_catalog -e SPRING_PROFILES_ACTIVE=testdata catalog-service
```
remove containers:
```shell
docker rm -f catalog-service polar-postgres
```
build and publish image
```shell
./gradlew bootBuildImage --imageName=ghcr.io/balanici/catalog-service --publishImage -PregistryUrl=ghcr.io -PregistryUsername=<your_github_username> -PregistryToken=<your_github_token>
```

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

