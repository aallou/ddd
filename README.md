# Description

It's a simple example to practice DDD using hexagonal architecture.

## Tools
- Java 21
- Gradle
- Spring boot

## Run tests
gradle test

## Run app
gradle bootRun

The app is available on http://localhost:8080

## use cases
### Create user
`curl --location --request POST 'http://localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "Joe",
"lastName": "Doe"
}'`

### Search user by id
`curl --location --request GET 'http://localhost:8080/api/users/15dd443a-6dc2-4d8d-855b-d8c8ef0c8c79'`
