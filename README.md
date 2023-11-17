# Acebook

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

- Install chromedriver using `brew install chromedriver`
- Start the server in a terminal session `mvn spring-boot:run`
- Open a new terminal session and navigate to the Acebook directory
- Run your tests in the second terminal session with `mvn test`

> All the tests should pass. If one or more fail, read the next section.

## Common Setup Issues

### The application is not running

For the feature tests to execute properly, you'll need to have the server running in one terminal session and then use a second terminal session to run the tests.

### Chromedriver is in the wrong place

Selenium uses Chromedriver to interact with the Chrome browser. If you're on a Mac, Chromedriver needs to be in `/usr/local/bin`. You can find out where it is like this `which chromedriver`. If it's in the wrong place, move it using `mv`.

### Chromedriver can't be opened

Your Mac might refuse to open Chromedriver because it's from an unidentified developer. If you see a popup at that point, dismiss it by selecting `Cancel`, then go to `System Preferences`, `Security and Privacy`, `General`. You should see a message telling you that Chromedriver was blocked and, if so, there will be an `Open Anyway` button. Click that and then re-try your tests.

## Existing features

This app already has a few basic features
* A user can sign up at `/users/new`
* A signed up user can sign in at `/login`
* A signed in user can create posts at `/posts`
* A signed in user can sign out at `/logout`

## Design

This app uses the repository pattern. The repository pattern separates the business logic of models from the responsibility of connecting to the database and making queries. Take a look in the `src/main/java/repository` and you'll find `PostRepository` which generates and executes queries to Create, Read, Update and Delete (CRUD) posts. Depending on what you've built in the past, it might or might not feel familiar to you.

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

# Zi-Tong's guide to running this on a kinda-of rooted Google-owned chromebook :coolsunglasses:
## Ideally...
- Try using podman (cooler, non-root version of docker)
## If this doesn't work...
- In the chromeOS linux dev environment install all the boring stuff (java17, postgres15, etc.)
  - Note: for postgres to work you have to run sudo su postgres, set the password = e.g. password
  - It takes you into a new environment and then you can run psql happy days :)
  - Then add in your local application-dev.properties (.gitignore your special one)
    - spring.datasource.username=postgres
      spring.datasource.password=password
- Install chromedriver (keep an eye on version) and mv to same place directory recommended in this project
- Install the MATCHING Chrome version (onto your linux distro)
  - Go to google.com/chrome and look at linux for latest
  - In my case I had to go hunting for a cached version of chrome to match with my outdated chromedriver
  - My fans got pretty loud running this old version of chrome... but it worked...
- Do the normal readme stuff above :)

# Zi-Tong's guide to running this on VSCODE/Docker dev containers
- Build container
- Enter postgresql password (postgres) into cli prompt
- Change application-dev properties:
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    ```
- Run mvn build commands :)
