# Movie Fanatic
MovieFanatic is a dynamic web application with an intuitive user interface that allows both guests and logged in members to the website to view a featured movie, the most popular movies, best reviewed movies of the year, comedies and dramas all on the home page. When a movie is selected, the user is brought to the movie view, where everything from the director, producers, and cast to the tag line, imdb and rotten tomatoes scores, plot summary and more is available to see. Click on a director, actor or producer in the movie view, and you are taken to a different view, where you can see an actor or director’s profile image, birthdate, popularity, and biography. Below there is also a display showing the movies that the actor or director has appeared in. Throughout the application, there is a search bar near the top of the page where users can search for any movie by title, as well as actors and directors by name. Once logged in, additional features are available to the user such as rating a movie, adding a movie to a favorite list and adding reviews for each movie.

- Spring code: /fanatic-spring
- Angular code: /fanatic-angular

Repository Link:  
- Spring code: https://github.com/pgerringer/fanatic/tree/master/MovieFanatics
- Angular code: https://github.com/pgerringer/fanatic-client/tree/master/MovieFanatic

# Roles / Responsibilities
* Responsible for pitching project idea with an Angular 2 demo pulling from an online movie API.
* Created the basic frames of the Angular 2 project as well as the Eclipse Java Spring workspace that was used as the basis of our coding efforts.
* Built the Header Component (Angular 2) for the project which consisted of login/logout and search functionality for the application.
* Coded the basic logic to pull from The Movie Database (TMDb) and The Open Movie Database (OMDb) APIs.
* Built the Movie View and Movie Review  Components (Angular 2) of the Movie Fanatics project which display movie details and movie reviews to the visitor.
* Combined Spring MVC Front End Controllers with Spring Data Repositories to read as well as create reviews from an Oracle database. 
* Used Spring's EntityManager to run extensive detailed PL/SQL queries against the Oracle database to determine aggregate data such as average movie ratings, counts of thumbs up and thumbs down on reviews, as well as number of reviews.
* Responsible for creating the basic data entity relationships and producing the final ERD.
* Acted as Revision Manager by organizing the two projects (front end and back end) in GitHub.
* Acted as CI/CD Manager by setting up the build Pipelines for the project on an AWS EC2.
* Used Jenkins to listen for webhooks to fire from GitHub.
* Built the Jenkins' Jobs to, based on the signals from GitHub would fire off and clone the GitHub repository, use Maven to build, and then deploy the artifacts to target contexts.
* Built an AWS S3 to host the artifacts from the Angular 2 Maven build.

# Git Flow
* Each team will have its own Github repository which will have a master branch deployed on an EC2 instance, and a development branch
* Feature branches specific to user stories/tasks will be made off of the dev branch
* Master and dev branches will be protected
* Pull requests made for each merge should be approved by designated team member/"git master"
* Do not change more than 5 files in a single pull request
* Tech Stack and Requirements
* Java 8, AWS RDS, CI/CD Pipeline(EC2, Maven, Jenkins, GitHub, Slack), Spring ORM/Spring Data, Spring MVC, Angular
* Logging and unit testing
* Detailed Github Wiki documentation

# Environment / Technologies
* EC2 
* Agile
* S3
* RDS
* DevOps
* Continuous Integration
* Spring
* Angular
* HTML5
* Bootstrap
* Java 1.8
