spring-security-auth0-example
=====================

This is a working skeleton using auth0 and spring security.

Simply edit the [auth0.properties|src/main/resources/auth0.properties] with your client id and your client secret and client domain.

```Shell
auth0.clientId=YOUR_CLIENT_ID
auth0.clientSecret=YOUR_CLIENT_SECRET
auth0.domain=YOUR_CLIENT_DOMAIN
```

Go to Auth0 dashboard and add http://localhost:8080/spring-auth0-example/ as an App Callback URL.

That's it, you can now run:

```Shell
mvn jetty:run
```

### Questions / feedback / comments?
[Contact us](http://www.nextprot.org/contact/us)
