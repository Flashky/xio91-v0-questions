# xio91-v0-questions

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f22d4845df7540109bbb24d3c3f131dc)](https://www.codacy.com/gh/Flashky/xio91-v0-questions/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Flashky/xio91-v0-questions&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/f22d4845df7540109bbb24d3c3f131dc)](https://www.codacy.com/gh/Flashky/xio91-v0-questions/dashboard?utm_source=github.com&utm_medium=referral&utm_content=Flashky/xio91-v0-questions&utm_campaign=Badge_Coverage)

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

Check the Authorization Server provider documentation to know how to set it up up and how to authenticate on it.

### Scopes

The service has the following scopes defined:

- write_questions: allows to perform creation or update on questions.

The scopes must be configured in the selected authorization server.

## API Reference

Currently, the API supports the following methods:

**Method**|**Endpoint**|**Authentication**|**Scope**
:-----:|:-----:|:-----:|:-----:
GET|/questions|None|None
GET|/questions/{id}|None|None
POST|/questions|Yes|write\_questions
PUT|/questions/{id}|Yes|write\_questions

  



