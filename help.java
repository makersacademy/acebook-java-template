/*
- maven to build the project:
  Kommandozeilenwerkzeug, aus der Kategorie der Build-Werkzeuge..
  brew install maven
  mvn -v
  mvn spring-boot:run
  maven test

- thymeleaf for templating


flyway to manage postgres db migrations
selenium for feature testing
faker to generate fake names for testing
junit4 for unit testing
spring-security for authentication and user management


MAVEN : https://maven.apache.org/pom.html

- i can explain what pom.xml is for:
POM stands for "Project Object Model". It is an XML representation of a Maven project held in a file named pom.xml.
When in the presence of Maven folks, speaking of a project is speaking in the philosophical sense, beyond a mere collection of files
containing code. A project contains configuration files, as well as the developers involved and the roles they play,
the defect tracking system, the organization and licenses, the URL of where the project lives, the project's dependencies,
and all of the other little pieces that come into play to give code life. It is a one-stop-shop for all things concerning the project.
In fact, in the Maven world, a project does not need to contain any code at all, merely a pom.xml.

- i can start the app using maven
  mvn -v
  mvn spring-boot:run
  mvn test


THYMELEAF : https://www.thymeleaf.org/ : https://www.thymeleaf.org/ecosystem.html
  Thymeleaf Testing Library : https://github.com/thymeleaf/thymeleaf-testing

  The Thymeleaf Testing library allows developers to create automated tests for Thymeleaf applications and extensions in a very easy and declarative way.
  Among its features:
    - independent library, callable from multiple testing frameworks like e.g. JUnit.
    - Tests only the view layer: template processing and its result.
    - Spring Framework and Spring Security integration.
  
  Thymeleaf + Spring Security
    - Adds new expression utility objects like #authentication and #authorization for integrating Spring Security capabilities into Thymeleaf expressions.
    - Adds new attributes like sec:authentication and sec:authorized for easier configuration of security.



  */
  System.out.println("Hello World");
  <li> th:text="${post.likes}" </li>

  <style>
  form {display: inline-block;}
</style>

<form style="display:inline-block" >

<li> <span th:text="${post.content}" ></span> &nbsp <span th:text="${post.likes}" ></span>   </li>
<p>Classic - Pepperoni, Olives, Onions <span class="text-orientation-right-css">11.99</span></p>

<form style="display:inline-block" action="#" th:action="@{/posts/likes/up}"  method="post" >
        <input type="hidden" id="up" name="up" th:attr="name='id'" th:value="${post.id}" >    
        <input type="submit" value="Like" /> 
        </form>  
         
        <input type="hidden" th:field="${post.id}" th:field="${post.content}" th:field="${post.likes}"  > 

        <button class='delete' data-id="${post.id}">Delete</button> 


        @PostMapping("/posts/likes/up")
        public RedirectView updatePosts(@ModelAttribute Post post  ){ 
         
        //Post upPost = post;
       // Short value = post.getLikes()  ;
        //++ value;
        //post.setLikes(value);

        repository.save(post);
        //Long id2 = Long.parseLong(id) ;
        //System.out.println("Hello World");
        //Post a = posts;
        //Short likes = post.getLikes();
        //likes = (short) (likes + 1);
        //Long id = posts.id;
        
        //repository.existsById(id2);
        //Optional<Post> a = repository.findById(id2);
        //post.setLikes(1);
         
        
        //post.setId(post.id);
        //post.setLikes(likes);
        //post.setContent(post.getContent()); 
        //post.setContent("Check1"); 
        //repository.save(post);

        return new RedirectView("/posts");

        <form style="display:inline-block" action="#" th:action="@{/posts/likes/up}" th:object="${post}" method="post" >
          
        <input type="submit" value="Like" /> 
        </form>  
         
         &nbsp&nbsp <span th:text="${post.likes}">  </span> &nbsp&nbsp
        
        <form style="display:inline-block" action="#" th:action="@{/posts}" th:object="${likes}" method="post" >
        <input type="hidden" id="dislike" name="dislike" value="down">    
        <input type="submit" value="Dislike" />
        </form> 