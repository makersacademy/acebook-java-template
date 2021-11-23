# AceVentura

This is a java/springboot project to be developed on.  There's a video tour of the application [here](https://youtu.be/L1Zi9WOJ6xg) but you should read through these docs first.

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
- Create a new Postgres database called `acebook_springboot_development`
- Install Maven `brew install maven`
- Build the app and start the server, using the Maven command `mvn spring-boot:run`
> The database migrations will run automatically at this point
- Visit `http://localhost:8080/users/new` to sign up

## Running the tests

- Make sure chromedriver is installed
- You might also need geckodriver
- Start the server in a terminal session `mvn spring-boot:run`
- Start a new terminal session, navigate to the Acebook directory and then do `mvn test` to run both feature tests and unit tests

## Existing features

This app already has a few basic features
* A user can sign up at `/users/new`
* A signed up user can sign in at `/login`
* A signed in user can create posts at `/posts`
* A signed in user can sign out at `/logout`

## Design

This app uses a structure that is similar to Bookmark Manager. The biggest difference is that, here, the repository 
pattern is used. The repository pattern separates the business logic of models from the responsibility of 
connecting to the database and making queries. Take a look in the `src/main/java/repository` and you'll find 
`PostRepository` which generates and executes queries to Create, Read, Update and Delete (CRUD) posts. 

## Initial learning goals

You don't need an in-depth knowledge of each dependency listed above. Once you can tick off these learning goals,
you're ready to dive in.  It's assumed that you can already TDD the Takeaway Challenge, or something of similar
complexity, in Java. It's OK if you need to pause here with Acebook and learn how to do that now :)

### Maven
- [ ] I can explain what pom.xml is for
- [ ] I can start the app using Maven

### Thymeleaf
- [ ] I can explain the code in `posts/index.html`
- [ ] I can plan a new template that could be used for editing a post

### Flyway
- [ ] I can explain what a migration is
- [ ] I can explain when migrations are run
- [ ] I can explain the code in the two migration files in this directory `/db/migration/`
- [ ] I can explain the naming convention for flyway migration files

### Selenium
- [ ] I can explain the code in `SignUpTest.java`
- [ ] I can write a new feature test for unsuccessful sign up

### Faker
- [ ] I can explain what Faker does
- [ ] I can explain why it's useful

### JUnit4
- [ ] I can explain the code in `PostTest.java`
- [ ] I could add more test cases to `PostTest.java`

### The repository pattern
- [ ] I can explain the repository pattern

### SpringBoot
- [ ] I can diagram how this SpringBoot application handles `GET "/posts"`

### Spring Security
- [ ] I can explain how this app is secured

## Resources


* [Some great videos on Spring Security](https://www.youtube.com/watch?v=sm-8qfMWEV8&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE).  Don't watch them all, but do watch the first couple if you want an overview.

