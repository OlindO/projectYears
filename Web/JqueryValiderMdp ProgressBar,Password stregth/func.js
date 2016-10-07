$(document).ready(function(){

	$('#password').keyup(function(){
			var password = $(this).val();
			password = $.trim(password);
			var lettre_min = /[a-z]/;
			var lettre_maj = /[A-Z]/;
			var nombre = /[0-9]/;
			if(password.length !=0){
				$('.error').hide();

				//password faible 
				if ((password.match(lettre_min)) && password.length < 4){
					$('.bar').animate({width:'10%'},200).show();
					$('.bar').css('background-color', 'red');
					$('.bar').text('Faible').show();
				}
				else if ((password.match(lettre_maj)) && password.length > 4){
					$('.bar').animate({width:'10%'},200).show();
					$('.bar').css('background-color', 'orange');
					$('.bar').text('Moyens').show();
				}
				else if ((password.match(lettre_min)) && password.length < 2) && ((password.match(lettre_maj)) && password.length > 2) && ((password.match(nombre)) && password.length < 3){
					$('.bar').animate({width:'10%'},200).show();
					$('.bar').css('background-color', 'blue');
					$('.bar').text('Correct').show();
				}
			}
			else
			{
				$('.error').fadeIn().text("Veuillez saisir un mot de passe");
				$('.bar').hide();
			}
		});
});