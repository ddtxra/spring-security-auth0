spring-security-auth0
=====================

An integration of spring security with Auth0.
This module is intented to include in your existing spring mvc project.

Build and install the project using the maven command:

```Shell
mvn install
```

Add the dependencies to your existing maven project
```XML
<dependency>
	<groupId>sib.calipho</groupId>
	<artifactId>spring-security-auth0</artifactId>
	<version>0.0.1</version>
</dependency>
```

Include in your existing Spring project the Auth0 configuration
```XML
	<import resource="classpath:auth0-security-context.xml" /> 
```

Set the properties auth0.clientSecret and auth0.clienId in a property file read from spring property place holder:
```Shell
auth0.clientId=YOUR_CLIENT_ID
auth0.clientSecret=YOUR_CLIENT_SECRET
```

That's it you should be able to run the spring security with Auth0 and access the UserDetails in your SecurityContext.

Please update for any feedback or comment! 

### Advanced configurations
TODO
