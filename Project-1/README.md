# The Expense Reimbursement System(ERS)
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

Specifications:  https://github.com/revature-associate-code-samples/Code-samples-for-Phillip-Gerringer/blob/master/Project-1/Project1.pdf
 
 Repository Link: https://github.com/1810-oct22-java/1810-oct22/tree/Phil_Gerringer/Phil_Gerringer_Code/Project1

### Roles / Responsibilities
* Used HTML for the presentation layer
* Utilized JavaScript for manipulating the HTML
* Used CSS to style the presentation layer
* Separated JavaScript and HTML into "modules" to ease coding efforts
* Used FrontController design pattern to serve the HTML simulating a single page application
* Servlets used to connect the presentation layer to the middle layer
* Middle tier consisted of Servlets calling Services containing the underlaying business logic.
* Services in the middle layer called Data Access Objects classes to connect to the Oracle DB in the back end.
* Used true Java Beans to be used by the DAO as representations of entities in the database
 
### Requirements
* The application shall employ the DAO design pattern, and properly separate your code into the appropriate layers
* The back-end system shall use JDBC to connect to an Oracle 12c EE database (Use at least one of each of our three statements - Statement, PreparedStatement, and CallableStatement)
* The application shall deploy onto Tomcat Server
* The middle tier shall use Servlet technology for dynamic Web application development 
- The front-end view can use JavaScript/jQuery with AJAX or Angular to make a single page application that uses AJAX to call server-side components 
- Use Log4J  
- Passwords shall be encrypted(optional) in Java and securely stored in the database 
- Users can upload a document or image of their receipt when submitting reimbursements(optional) 
- The application will send an email to employees letting them know that they have been registered as a new user, giving them their temporary password(optional) 
 
### Tech Stack
* Java 1.8
* Oracle DB (Used MySQL local database due to performance issues with AWS and Oracle install issues on a Macbook)
* JDBC
* Java Stored Procedures
* JavaScript
* jQuery
* AJAX
* Tomcat Server
