spring-security-auth0
=====================

An integration of spring security with Auth0

Build and install this project using the maven command:

```Shell
mvn install
```

Add the dependencies to your project

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

In order to set the correct client id and client secret, your should use the Spring Security Place Holder to read a file with your auth0.clientSecret and auth0.clientId given by Auth0. Or for the purpose of testing you can simply change the code
```XML
	<bean id="auth0AuthenticationProvider" class="ddt.spring.security.auth0.Auth0AuthenticationProvider">
		<property name="clientSecret" value="${auth0.clientSecret}" ></property>
		<property name="clientId" value="${auth0.clientId}" ></property>
	</bean>
```
