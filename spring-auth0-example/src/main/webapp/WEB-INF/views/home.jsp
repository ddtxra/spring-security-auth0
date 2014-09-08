<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript">
	var auth0ClientId = '<c:out value="${id}"/>';
	var auth0ClientDomain = '<c:out value="${domain}"/>';
</script>

<script src="http://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<script src="https://cdn.auth0.com/w2/auth0-widget-4.js"></script>

<script type="text/javascript" src="//use.typekit.net/iws6ohy.js"></script>
<script type="text/javascript">
	try {
		Typekit.load();
	} catch (e) {
	}
</script>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- font awesome from BootstrapCDN -->
<link
	href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">


<script src="js/app.js">
	
</script>
<link href="css/app.css" rel="stylesheet">
</head>
<body class="home">
	<div class="container">
		<div class="login-page clearfix">
			<div class="login-box auth0-box before">
				<img src="https://i.cloudup.com/StzWWrY34s.png" /> 
				<img src="http://www.javatpoint.com/images/spimages/spring1.png"/>
				<h3>Auth0 Example with Spring</h3>
				<a ng-click="login()" class="btn btn-primary btn-lg btn-login">Sign In</a>
				<a ng-click="logout()" class="btn btn-primary btn-lg btn-logout">Log Out</a>
				<span class="nickname"></span>
				
				<div align="center"><textarea id="token"></textarea></div>
				
			</div>
			<div class="logged-in-box auth0-box logged-in">
				<button class="btn btn-lg btn-primary btn-api">Call API secured method</button>
			</div>
		</div>
	</div>
</body>
</html>
