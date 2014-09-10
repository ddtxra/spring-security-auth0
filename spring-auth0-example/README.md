spring-security-auth0-example
=====================

This is a working skeleton using auth0 and spring security.

Running
==

Edit the [src/main/resources/auth0.properties](src/main/resources/auth0.properties) with your client id, secret and domain.

```Shell
auth0.clientId=YOUR_CLIENT_ID
auth0.clientSecret=YOUR_CLIENT_SECRET
auth0.domain=YOUR_CLIENT_DOMAIN
```

Go to Auth0 dashboard and add [http://localhost:8080/spring-auth0-example/](http://localhost:8080/spring-auth0-example/) as an App Callback URL.

You should now be able to run the application:

```Shell
mvn jetty:run
```

And access: [http://localhost:8080/spring-auth0-example/](http://localhost:8080/spring-auth0-example/)

### Questions / feedback / comments?
[Contact us](http://www.nextprot.org/contact/us)
