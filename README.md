# The task
# Coding Kata
All technical interviews taken with _Haiilo_ will be performed in the same manner, in order to minimise variance in the recruitment process. This should ensure the interview process is fair to the candidates, and also that _Haiilo_ do not miss opportunities to hire good candidates.
The interview exercise that we will use during in person technical interviews is the "Checkout Kata", which is described below, with annotations. The wording of the kata has been carefully crafted to provide a specific set of challenges and crutches to a candidate.

## The Exercise:
### Implement the code for a supermarket checkout that calculates the total price of a number of items.
### Items each have their own price, which can change frequently.
### There are also weekly special offers for when multiple items are bought.
### An example of this would be "Apples are 50 each or 3 for 130".

### The pricing table example:
| Item   |Price for 1 item | Offer                |
|--------|-----------------|----------------------|
| Apple  | 30              | 2 for 45             |
| Banana | 50              | 3 for 130            |
| Peach  | 60              |  -                   |
| Kiwi   | 20              |  -                   

The checkout accepts the items in any order, so that if we scan an apple, a banana and another apple, we'll recognise two apples and apply the discount of 2 for 45.


# Checkout

This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/).

## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

In addition to the Spring Boot application, the development server must also be started - for this
[Node.js](https://nodejs.org/) version 22 is required. Angular CLI and required dependencies must be installed once:

```
npm install -g @angular/cli
npm install
```

The development server can be started as follows:

```
ng serve
```

Your application is now accessible under `localhost:4200`.

Add code using Angular schematics with `ng generate ...`.
Frontend unit tests can be executed with `ng test`.
Generate a messages.json for translation with `ng extract-i18n --format=json`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/checkout-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=com.haiilo/checkout
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/reference/jpa.html)
* [Learn Angular](https://angular.dev/tutorials/learn-angular)  
* [Angular CLI](https://angular.dev/tools/cli)
* [Tailwind CSS](https://tailwindcss.com/)  
