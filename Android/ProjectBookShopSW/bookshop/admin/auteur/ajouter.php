<?php
if(isset($_POST["Ajouter"])){
	$post_nom = str_ireplace("'", "''", $_POST["nom"]);
	$post_prenom = str_ireplace("'", "''", $_POST["prenom"]);
	//--
	$auteur = new Auteur();
	$auteur->setNom($post_nom);
	$auteur->setPrenom($post_prenom);
	$daoAuteur = new DAOAuteur();
	$resultat = $daoAuteur->ajouter($auteur);
	if($resultat){
		echo "<h4 class='succes'>$resultat : Auteur ajouté avec succès<h4>";
	}
	else{
		echo "<h4 class='erreur'>$resultat : Auteur non ajouté<h4>";
	}
}
?>
<h2>Ajouter un auteur</h2>
<p><input type="text" name="nom" placeholder="Saisissez le nom" required></p>
<p><input type="text" name="prenom" placeholder="Saisissez le prénom" required></p>
<p><input type="submit" name="Ajouter" value="Ajouter l'auteur"></p>