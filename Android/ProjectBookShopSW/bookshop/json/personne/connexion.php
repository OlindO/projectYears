<?php
if(isset($_POST["email"]) && isset($_POST["password"])){
	$email = str_ireplace("'", "''", $_POST["email"]);
	$password = str_ireplace("'", "''", hash("sha256", $_POST["password"]));
	//--
	$daoPersonne = new DAOPersonne();
	$personne = new Personne();
	$personne = $daoPersonne->getPersonneBy($email, $password);
	//--
	if($personne){
		$resultat = array(
				"id" => $personne->getId(),
				"civilite" => $personne->getCivilite(),
				"nom" => $personne->getNom(),
				"prenom" => $personne->getPrenom(),
				"email" => $personne->getEmail(),
				"password" => $personne->getPassword(),
				"photo" => $personne->getPhoto());
		echo json_encode($resultat);
		exit();
	}
	else{
		echo "Erreur : Login ou Mot de passe incorrectes";
		exit();
	}
}

?>