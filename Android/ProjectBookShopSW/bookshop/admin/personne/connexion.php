<?php
if(isset($_POST["Connecter"])){
	$email = str_ireplace("'", "''", $_POST["email"]);
	$password = str_ireplace("'", "''", hash("sha256", $_POST["password"]));
	//--
	$daoPersonne = new DAOPersonne();
	$personne = $daoPersonne->getPersonneBy($email, $password);
	echo "<pre>"; print_r($personne); echo "</pre>";
}
?>
<h2>Connecter une personne</h2>
<p><input type="email" name="email" value="" placeholder="Votre adresse email" required></p>
<p><input type="password" name="password" value="" placeholder="Votre mot de passe" required></p>
<p><input type="submit" name="Connecter" value="Se connecter" required></p>
