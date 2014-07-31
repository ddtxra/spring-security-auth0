spring-security-auth0
=====================

[![Build Status](https://travis-ci.org/ddtxra/spring-security-auth0.svg?branch=master)](https://travis-ci.org/ddtxra/spring-security-auth0)

An integration of spring security with <a href="http://auth0.com">Auth0</a>.
This module is intended to be include in your existing spring mvc project.

This module was tested with Spring Security 3.2.4.

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

Include in your existing Spring project the Auth0 configuration (you will need to have the spring springSecurityFilterChain defined on your web.xml)
See spring
```XML
	<import resource="classpath:auth0-security-context.xml" /> 
```

Set the Auth0 properties in a property file read from spring property placeholder.
```Shell
auth0.clientId=YOUR_CLIENT_ID
auth0.clientSecret=YOUR_CLIENT_SECRET
```

That's it! You should be able to run the spring security with Auth0 and access the [Auth0UserDetails](src/main/java/sib/calipho/spring/security/auth0/Auth0UserDetails.java) as principal in your SecurityContext.

The username returned by UserDetails is the email if it exists, otherwise it is the user_id.
The user role associated to an user is the USER_ROLE.
Don't hesitate to change this behaviour associated to your needs.


### Advanced configurations
Don't include the [auth0-security-context.xml](src/main/resources/auth0-security-context.xml) in your application context. Edit it, according to your needs.

### Questions / feedback / comments? 
[Contact us](http://www.nextprot.org/contact/us)


