# com.vallete.portfolio.backend-java
*The following project is a part of a whole project, in which the backend-java is
the provider of APIs being the layer to get and set data in our Postgresql database.*

*The main goal of the project com.vallete.portfolio is to create and turn the code
available for intermediate developers, which with some knowledge are in limbo
between being a beginner and next-level software developer. The concept behind
this may be applied in a real application with handling data, security and best practices.*

*The project contains common use cases with iterations among posts, comments,
billings, and partners.*

## Technologies of the Full Project
- Java - Spring Boot as backend in a Tomcat server
- React - NextJS as frontend in a Vercel server
- Postgresql as the database in a Linux server

**As uncoupled layers you are able to use any kind of server as Linux/Windows,
Vercel/Netlify, Amazon, or similar.*

## Required components for the backend and its versions
| Software           | Version       |
| -------------      |:-------------:|
| JDK                | 19            |
| Postgresql         | 15            |
| Eclipse or similar | any           |
| Postman or similar | any           |


## Postgresql
- Open the Postgres PgAdmin and create a database called **portfolio** and save
  the password.

## Spring Boot
- With the project opened in the IDE all the dependencies will be automatically
  downloaded through Maven with no action needed.
- Rename the file com.vallete.portfolio.backend-java\src\main\resources\application_properties_RENAME_IT
  to application.properties and make sure that the configurations are correct
  including the password used on the database creation.
- Run the project and try to request to some endpoint of our API.

## Endpoints
1. /portfolio
   1. /user
      1. /register [POST]
      2. /login [POST]
      3. /{idUser}/balance [GET, token is required]
   2. /post
      1. / [GET, token required]
      2. / [POST, body and token are required]
   3. /comment
      1. / [POST, body and token are required]
   4. /transaction
      1. /ok [GET]
      2. / [GET, POST, PUT, body and token are required]
      3. /{idTransaction} [DELETE, token is required]

/*/*/*/*/*/*/*/
###### Only for reference 
![Some alt text.](https://website.vallete.com/_next/image?url=%2F_next%2Fstatic%2Fimage%2Fpublic%2Fimages%2Fhome%2FwhenStartedLarge.943d34f87ea1b4b03d0f272f60a8b1f2.png&w=750&q=75 "alt message here")

Some link [text link](https://api.vallete.com/portfolio/ok)....

> Markdown parent
>> Markdown children


```
let message = 'This is a block code';
alert(message);
```

This is an `inline code`.