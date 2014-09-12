# Auth0 + NodeJS API Seed
This is the seed project you need to use if you're going to create a Java API that will use Spring Security & Spring. You'll mostly use this API either for a SPA or a Mobile app. If you just want to create a Regular Java WebApp, please check [this other seed project](https://github.com/auth0/auth0-java/tree/master/examples/java-regular-webapp)

#Running the example
In order to run the example you need to have `maven` and `java` installed.

You also need to set the ClientSecret, ClientId and Domain for your Auth0 app. To do this, you can either [go to the tutorial](https://docs.auth0.com/server-apis/java-spring-security) and download a Seed project with all your keys configured or you can do some of the following.

You can set these variables in the `auth0.properties` file, or you can set them as maven arguments when running the app (with `-Dauth0.clientSecret=secret -Dauth0.clientId=clientid -Dauth0.domain=yourdomain.auth0.com`)

Once that's done, just run `mvn jetty:run`. and try calling [http://localhost:3001/ping](http://localhost:3001/ping)
