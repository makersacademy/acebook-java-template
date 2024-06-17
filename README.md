# Socialites

The application uses:
  - `maven` to build the project
  - `thymeleaf` for templating
  - `flyway` to manage `postgres` db migrations
  - `selenium` for feature testing
  - `faker` to generate fake names for testing
  - `junit4` for unit testing
  - `spring-security` for authentication and user management
  
Below, you'll find specific learning objectives for each tool.

## QuickStart Instructions

- Fork and clone this repository to your machine
- Open the codebase in an IDE like InteliJ or VSCode
- Create a new Postgres database called `socialites_springboot_development`
- Install Maven `brew install maven`
- Build the app and start the server, using the Maven command `mvn spring-boot:run`
> The database migrations will run automatically at this point
- Visit `http://localhost:8080/users/new` to sign up

## Running the tests

- Install chromedriver using `brew install chromedriver`
- Start the server in a terminal session `mvn spring-boot:run`
- Open a new terminal session and navigate to the Acebook directory
- Run your tests in the second terminal session with `mvn test`

> All the tests should pass. If one or more fail, read the next section.

## Resources


* [Some great videos on Spring Security](https://www.youtube.com/watch?v=sm-8qfMWEV8&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE).  Don't watch them all, but do watch the first couple if you want an overview.

