<?php
$id = isset($_GET["id"]) ? $_GET["id"] : 0;
$info = DAOAuteur::getById($id);
if(!$info) exit("Aucun auteur trouvé");

if(isset($_POST["Modifier"])){
	$post_id = str_ireplace("'", "''", $_POST["id"]);
	$post_nom = str_ireplace("'", "''", $_POST["nom"]);
	$post_prenom = str_ireplace("'", "''", $_POST["prenom"]);
	//--
	$auteur = new Auteur();
	$auteur->setId($post_id);
	$auteur->setNom($post_nom);
	$auteur->setPrenom($post_prenom);
	$daoAuteur = new DAOAuteur();
	$resultat = $daoAuteur->modifier($auteur);
	if($resultat){
		echo "<h4 class='succes'>Auteur modifié avec succès<h4>";
	}
	else{
		echo "<h4 class='erreur'>Auteur non modifié<h4>";
	}
	//--
	$info = DAOAuteur::getById($post_id);
}
?>
<h2>Modifier un auteur</h2>
<p><input type="text" name="nom" value="<?php echo $info->getNom(); ?>" placeholder="Saisissez le nom" required></p>
<p><input type="text" name="prenom" value="<?php echo $info->getPrenom(); ?>" placeholder="Saisissez le prénom" required></p>
<p><input type="hidden" name="id" value="<?php echo $info->getId(); ?>" required></p>
<p><input type="submit" name="Modifier" value="Enregistrer"></p>
<p><a href="?p=gerer_auteur&action=supprimer&id=<?php echo $id; ?>">Supprimer</a></p>