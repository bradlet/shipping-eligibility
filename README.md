# Shipping Eligibility
Shipping Eligibility is a web service which will determine a user's eligibility to gain 
access to a new shipping service.

### Development Information
Project developed by Bradley Thompson.  
This is project is being implemented as a solution to a take-home coding challenge,
part of the eBay interview process.

### Project Technologies
- This project is developed in [Kotlin](https://kotlinlang.org/).
- Application Framework: [Spring Boot](https://spring.io/projects/spring-boot).

### Build Instructions
Gradle's build scripts make building the Shipping Eligibility service fast and simple!
Just run the following command within the working directory...
> ./gradlew bootRun

### Testing
This application uses Spring Boot's built-in testing framework, JUnit5.
Enter the following command within the working directory to run all unit tests...
> ./gradlew clean testA

### Brief Code Overview
Basically, this API takes in a title, seller, category and price from a
GET request, and creates an instance of the EligibilityRequest class with that 
data. Then, it validates that EligibilityRequest, checks whether the
request is eligible for the shipping program and report whether it 
is eligible or not. 

### Using This Application
The main entry point for this application can be reached at...
> localhost:8080/checkAccess

There is no frontend for this part of the application. This entry
point is solely used to make calls to the API.

An example of an API request using correct formatting:
> localhost:8080/checkAccess?title=ItemName&seller=Username&category=1$price=2.99

To simplify making this request, I added a really basic html
form at *localhost:8080/*. Nothing fancy it simply sends a request
to the API and then shows the response body.

#### Eligibility Basis
The rules used to determine eligibility are held in 
resources/data. At the moment, this is simply three text files,
one each for the approved categories, enrolled sellers, and minimum price.

### Design Decisions & Assumptions
- I chose to use Kotlin because I have never even seen
Kotlin code before and wanted to work with it for fun. Also because
Jake, my interviewer, made the language sound intriguing.
- I chose to use a GET request for calls to the API, since new data
isn't being submitted. My assumption was that the assignment req' for
JSON communication was intended for the response type -- and Spring Boot's
built in plugin Jackson is supposed to convert to JSON.
- Choosing to use .txt for data persistence was a hard decision to make. 
On the one hand, it was fast to implement. On the other, it was a very
primitive design and didn't show much professional prowess. I considered 
using either a relational or non relational database, but it seemed a
little overkill, and this use case doesn't require relational queries. 
Just single column data storage which wastes some of the power of databases.
- I initially wanted to implemented following the builder pattern,
keeping an EligibilityRequest as a data class, and simply ensuring that
any time there is a built EligibilityRequest it is in a valid state. 
Given the size of the project, and the fact that I gravitate to the builder
pattern too often, I simply stuck to a more general OO approach.
- I separated the lists being compared to from an EligibilityRequest to 
make the application more modular.