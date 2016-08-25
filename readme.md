# Birthdaymail 

A application that check's a Database for entities how have today birthday.
Entities with birthday will be added to a mail and send to specified receiver.
In a `application.properties` you can define receivers and time to check birthday.
Key for receiver is `birthdaymail.eMailTo` and for check interval is `birthdaymail.cron.birthday.check`.


## Quickstart
```
git clone https://github.com/christianhenle/birthdaymail
cd birthdaymail
mvn clean package
java -jar -Dspring.profiles.active=dev target\*.jar
```

Browser to [localhost:8080](localhost:8080) shows people with today birthday.

## Project description

The project uses:

- [Spring Boot](http://github.com/spring-projects/spring-boot)
- [Spring (MVC)](http://github.com/spring-projects/spring-framework)
- [Spring Data JPA](http://github.com/spring-projects/spring-data-jpa)
- [Querydsl](www.querydsl.com)

## Requirements

- Java 8 JDK installed
