<?php
if(isset($_POST["Ajouter"])){
	
	// Traitement de la photo
	if(isset($_FILES["photo"])){
	
		$name= !empty($_FILES["photo"]["name"]) ? date("YmdHis")."-".$_FILES["photo"]["name"] : "";
		$type= $_FILES["photo"]["type"];
		$size= $_FILES["photo"]["size"];
		$temp= $_FILES["photo"]["tmp_name"];
		$error= $_FILES["photo"]["error"];
	
		if($error == 0)
		{
			$photo = Bibliotheque::removeaccents($name);
			move_uploaded_file($temp, "../upload/".$photo);
		}
	}
	
	$civilite = str_ireplace("'", "''", $_POST["civilite"]);
	$nom = str_ireplace("'", "''", $_POST["nom"]);
	$prenom = str_ireplace("'", "''", $_POST["prenom"]);
	$email = str_ireplace("'", "''", $_POST["email"]);
	$password = str_ireplace("'", "''", $_POST["password"]);
	//--
	$personne = new Personne();
	$personne->setNom($nom);
	$personne->setPrenom($prenom);
	$personne->setEmail($email);
	$personne->setPassword(hash("sha256", $password));
	$personne->setCivilite($civilite);
	$personne->setPhoto($photo);
	$daoPersonne = new DAOPersonne();
	$resultat = $daoPersonne->ajouter($personne);
	if($resultat){
		echo "<h4 class='succes'>$resultat : Personne ajoutée avec succès<h4>";
	}
	else{
		echo "<h4 class='erreur'>$resultat : Personne non ajoutée<h4>";
	}
}
?>
<h2>Ajouter une personne</h2>
<p>
	<select name="civilite" required>
		<option value="Madame">Madame</option>
		<option value="Mademoiselle">Mademoiselle</option>
		<option value="Monsieur">Monsieur</option>
	</select>
</p>
<p><input type="text" name="nom" value="" placeholder="Votre nom" required></p>
<p><input type="text" name="prenom" value="" placeholder="Votre prénom" required></p>
<p><input type="email" name="email" value="" placeholder="Votre adresse email" required></p>
<p><input type="password" name="password" value="" placeholder="Votre mot de passe" required></p>
<p><input type="file" name="photo" value="" placeholder="Photo"></p>
<p><input type="submit" name="Ajouter" value="Ajouter la personne" required></p>
