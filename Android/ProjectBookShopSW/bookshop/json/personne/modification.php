<?php
if(isset($_POST["id"]) 
	&& isset($_POST["email"])
	&& isset($_POST["password"]) 
	&& isset($_POST["nom"]) 
	&& isset($_POST["prenom"]) 
	&& isset($_POST["civilite"])){
	//--
	$id = $_POST["id"];
	$civilite = str_ireplace("'", "''", $_POST["civilite"]);
	$nom = str_ireplace("'", "''", $_POST["nom"]);
	$prenom = str_ireplace("'", "''", $_POST["prenom"]);
	$email = str_ireplace("'", "''", $_POST["email"]);
	$password = str_ireplace("'", "''", $_POST["password"]);
	$photo = "";
	//--
	$personne = new Personne();
	$personne->setId($id);
	$personne->setNom($nom);
	$personne->setPrenom($prenom);
	$personne->setEmail($email);
	$personne->setPassword(hash("sha256", $password));
	$personne->setCivilite($civilite);
	$personne->setPhoto($photo);
	$daoPersonne = new DAOPersonne();
	$personne = $daoPersonne->modifier($personne);
	//--
	if($personne)
		echo "OK : Modification effectuée avec succès.";
	else 
		echo "Erreur : Une problème est survenu pendant la modification";
}
?>