$(document).ready(
		function() {

			var widget = new Auth0Widget({
				domain : auth0ClientDomain,
				clientID : auth0ClientId,
				callbackURL : location.href,
				callbackOnLocationHash : true
			});

			var userProfile;

			
			$('.btn-logout').click(function(e) {
		    	localStorage.removeItem('userToken');
			    userProfile = null;
				$('#token').val("");
				$('.btn-login').show();
				$('.btn-logout').hide();
				$('#nickname').val("");
			})
			
			console.log("userToken" , localStorage.getItem('userToken'));

			if(localStorage.getItem('userToken')){
				$('.btn-login').hide();
				$('.btn-logout').show();
				$('#token').val(localStorage.getItem('userToken'));
				$('.nickname').val('someone');
			}else {
				$('.btn-login').show();
				$('.btn-logout').hide();
			}

			$('.btn-login').click(function(e) {
				e.preventDefault();
				widget.signin({ popup: true, scope : 'openid profile'}, null, function(err, profile, token) {
					if (err) {
						// Error callback
						console.log("There was an error");
						alert("There was an error logging in");
					} else {
						// Success calback

						// Save the JWT token.
						localStorage.setItem('userToken', token);
						$('#token').val(token);
						$('.btn-login').hide();
						$('.btn-logout').show();

						// Save the profile
						userProfile = profile;

						//$('.login-box').hide();
						//          $('.logged-in-box').show();
						$('.nickname').val(profile.nickname);
					}
				});
			});

			$.ajaxSetup({
				'beforeSend' : function(xhr) {
					console.log('before',$('#token').val())
					if (localStorage.getItem('userToken')) {
						xhr.setRequestHeader('Authorization', 'Bearer '
								+ $('#token').val());
					}
				}
			});

			$('.btn-api').click(function(e) {
				
				var urlSecured = location.href + "secured";
				$.ajax({
					url : urlSecured
				}).done(function(data, statusText, xhr) {
					//var status = xhr.status; //200
					alert(xhr.responseText);
					//var head = xhr.getAllResponseHeaders(); //Detail header info
				}).error(function(error, status, xhr) {
					//console.log(error, status);
					alert("Error: " + error.responseText);
				});
			})

		});
