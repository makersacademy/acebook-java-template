# Acebook-Beta

'Beta' is full stack java/springboot web application which is a Facebook clone. We worked on an existing codebase that had basic features and have successfully implemented many more features to ensure a great user experience. 

View 'Beta' here: https://acebook-beta.herokuapp.com/

## Tech Stack and Dependancies Used
Programming Languages:
  - `Java` - back end language
  - `HTML` - front end language
  - `CSS` - styling the front end

Database Management:
  - `PSQL` - PostgreSQL for database creation and management
  - `Heroku PSQL` - link local PSQL DB to Heroku 
  - `TablePlus` - UI for PSQL DB queries
  - `Flyway` - manage Postgres DB migrations

Core Frameworks:
  - `Maven` - build the project 
  - `Thymeleaf` - templating
  - `Springboot` - run application
  - `Spring Security` - User management and authentication

Testing:
  - `Junit4` - unit testing
  - `Faker` - generate fake user credentials for testing
  - `Maven Test` - run tests
  - `Selenium/Chromedriver` - web driver for feature testing

Hosting:
  - `Heroku` - host the web application

Additional Dependancies: 
  - `Pretty Time` - implement how long ago comments and posts were made
  
## Existing features
  - A user can sign up 
  - A signed up user can sign in and sign out 
  - A signed in user can:
    • create new posts
    • comment on all existing posts
    • 'like' existing posts
    • have a profile picture 
    • see how long ago posts were made 

## Run The App Locally
- Fork and clone this repository to your machine
- Open the codebase in an IDE like InteliJ or VSCode
- Create a new Postgres database called `acebook_springboot_development`
- Install Maven `brew install maven`
- Build the app and start the server, using the Maven command `mvn spring-boot:run`
> The database migrations will run automatically at this point
- Visit `http://localhost:8080/login.html` to sign up

## Running the tests
  - `brew install --cask chromedriver`
  - Start the server in a terminal session `mvn spring-boot:run`
  - Start a new terminal session, navigate to the `beta` directory and then do `mvn test` to run both feature tests and unit tests

## Resources

## Credits
Team members:
  - Aisha - (https://github.com/AishaDorsett)
  - Joseph - (https://github.com/Joseph112358)
  - Pinar - (https://github.com/pinteck)
  - Sunny - (https://github.com/sunnydonaldson)
  - Tommy - (https://github.com/tommyoswinwilliams)
