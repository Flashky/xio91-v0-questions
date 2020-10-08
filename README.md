# xio91-v0-questions

## External dependencies

### Databases

A MongoDB is needed as it is used by the service to persist the data.


## Configuration properties

You must set these configuration properties by overriding either ``application.yml`` or as environment variables before running the service.


Database configuration:

- ``spring.data.mongodb.uri``: The MongoDB uri.

Authorization server configuration:

- ``oauth.issuer-uri``: The Authorization Server issuer uri.

Sample for Okta: https://dev-1234.okta.com/oauth2/default

## Authentication

Service uses OAuth 2.0 authentication for consuming POST, PATCH and PUT endpoints.

Check the Authorization Server provider documentation to know how to authenticate on it.

### Scopes

The service has the following scopes defined:

- write_questions: allows to perform creation or update on questions.

## API Reference

**Method**|**Endpoint**|**Authentication**|**Scope**
:-----:|:-----:|:-----:|:-----:
GET|/questions|None|None
GET|/questions/{id}|None|None
POST|/questions|Yes|write\_questions
PUT|/questions/{id}|Yes|write\_questions

  



