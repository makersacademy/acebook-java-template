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

# Branch control

## Useful commands

- git branch -a (View all branches, an * will appear next to the current branch you are in) <br />
- git branch "branch name" (Creates a new branch, the speech marks are not required) <br />
- git checkout -b "branch name" (Creates and checks out a branch all in one command) <br />
- git checkout "branch name" (changes to a different branch. If the branch does not exist will throw error, don't worry just run the
command again and check spelling)<br />
- git merge "branch name" (Merges current branch onto main, add "--no--ff" to keep the branch commit history)

## Branch workflow

- Make sure we an up to date main branch, using git pull. <br />
- Create a new branch, naming it based on the ticket(s) we are currently working on. <br />
- Checkout our new branch and make the appropriate changes. <br />
- Make the changes and run git add and commit commands as normal. <br />
- Aim not to push new changes until they are complete but if we need to, so our pair can take over
for example, run the push command via the follwing step. <br />
- Git push will always point to the origin for the main branch to begin with, our first push on our new branch we must use 'git push --set-upstream origin "branch name". This will point our changes to our new branch instead of the main branch (that would be bad). We only have tp do this once, any further pushes we can use 'git push origin'. <br />
- Once happy, its time to merge. We can do this through the terminal or alternatively through GitHub directly. <br />
- For simplicity, stick to merging using GitHub. Set authorization if required and move your ticket from 'Doing' to 'Merge Ready'. <br />

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

