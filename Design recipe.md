Acebook


Questions
 - Are sign in and sign up pages the same?
  -- answer: Dom created seperate pages on Figma



Pages:


Acebook template currently has these features:

* A user can sign up at /users/new
* A signed up user can sign in at /login
* A signed in user can create posts at /posts
* A signed in user can sign out at /logout



Acebook pages to implement

(Ranked urgency 1 - 10 (1 most important, 10 least))


(1) Welcome page (Sign up)
NOTE: Spring Security has set up the login/lgout pages automatically.  You need to go around this to change the pages

Template currently only has (http://localhost:8080/users/new): 
 - Username field
 - Password field
 - Submit button
 - Reset button (what does this do?)

User stories for this page:
 - User can sign up to Acebook (part of the template)
 - User can log in to Acebook (part of the template)
 - If user signs up, they need to put:
	- Name
        - username field exists already
	- Email
        - need to create an email field
	- Password
        - password field exists already
	- Re-Enter Password
        - need to create Re-entry of password field 

(1) Welcome page (Sign in)
Template currently only has (http://localhost:8080/login):
- Username field
 - Password field
 - Sign in button

User stories for this page:
 - User can sign in by inputting Email and Password
 - User can click the sign in button (signs in if details are correct)
 - User can click the Sign Up button if they haven’t created an account


 (2) Feed

Template currently only has (http://localhost:8080/posts):
 - Content field
 - Submit button
 - Reset button
 - text bellow the buttons, showing text that was entered into Content field


User stories for this page:
 - Once user signed up/in, they arrive at feed page
 - User can see posts from friends
 - User can use Content field to post text and images
 - User can click ‘message’ button to message their friends
 - User can click button to go to their Profile page
 - User can click button to search and add friends (Follow Friend)
 - User can click button to go to settings


(3) myProfilePosts

User stories for this page:
 - User can see their image, name, about me
 - User can see their own posts by clicking the ‘My Posts’ button
 - User can see their friends posts by clicking the ‘My Friends’ button
 - User can click ‘Edit’ button on the comments form their posts 
 - User can click ‘Back to feed’ button
 - User can click ‘Follow friend’ button
 - User can click ‘Settings’ button


(4)editPost

User stories for this page:
 - When user clicks ‘Edit’ button in myProfilePosts, they arrive at this page
 - User can type in content field to replace previous post text
 - User can upload new image
 - User can click the ‘save’ button or ‘cancel’ button
 - User can click ‘Delete’ button


(5)myProfileFriends

User stories for this page:
 - If User clicks on a friends profile icon (anywhere in the app), they go to this page
 - User can see their friends profile image, name and about me
 - User can see their friends posts
 - User should be able to follow/add friend from here
 - User should be able to send a message to friend
 - User can click the ‘Back to feed’, ‘Back to Profile’ and ‘Settings’ buttons


(6)Message 

User stories for this page:
 - User can click the ‘message’ button from their profile page
 - Once user has clicked the ‘message’ button, user goes to message page
 - User can see the profile pic and name of friend they are messaging
 - User can see the list of message between them and their friend
 - User can type message in message field and click send

