spring-security-auth0
=====================

Build and install the project using the maven command:

```Shell
mvn install
```

Add the dependencies to your existing maven project
```XML
<dependency>
	<groupId>sib.calipho</groupId>
	<artifactId>spring-auth0-core</artifactId>
	<version>0.0.1</version>
</dependency>
```

Include in your existing Spring project the Auth0 configuration (you will also need to configure the spring security filter [springSecurityFilterChain](http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html) in your web.xml)
```XML
	<import resource="classpath:auth0-security-context.xml" /> 
```
This configuration will secure URL starting with /secured/* . Feel free to modify it according to your needs.

Set the Auth0 properties in a property file read from spring property placeholder.
```Shell
auth0.clientId=YOUR_CLIENT_ID
auth0.clientSecret=YOUR_CLIENT_SECRET
```

That's it! You should be able to run the spring security with Auth0 and access the [Auth0UserDetails](src/main/java/sib/calipho/spring/security/auth0/Auth0UserDetails.java) as principal in your SecurityContext.

The username returned by UserDetails is the email if it exists, otherwise it is the user_id.
The roles associated to a user are the ones set in the profile.roles attribute by auth0 (one can set a custom rule). If the attribute roles is not set, the USER_ROLE role will be given by default.
The other Auth0 attributes can be accessed via the method getAuth0Attribute. Example getAuth0Attribute("picture"), getAuth0Attribute("gender") ... 
Don't hesitate to change this behaviour associated to your needs.


### Advanced configurations
Edit the [auth0-security-context.xml](src/main/resources/auth0-security-context.xml) according to your needs.
