<?php
$id = isset($_GET["id"]) ? $_GET["id"] : 0;
$info = DAOTheme::getById($id);
if(!$info) exit("Aucun theme trouvé");

if(isset($_POST["Modifier"])){
	$post_id = str_ireplace("'", "''", $_POST["id"]);
	$post_libelle = str_ireplace("'", "''", $_POST["libelle"]);
	//--
	$theme = new Theme();
	$theme->setId($post_id);
	$theme->setLibelle($post_libelle);
	$daoTheme = new DAOTheme();
	$resultat = $daoTheme->modifier($theme);
	if($resultat){
		echo "<h4 class='succes'>Theme modifié avec succès<h4>";
	}
	else{
		echo "<h4 class='erreur'>Theme non modifié<h4>";
	}
	//--
	$info = DAOTheme::getById($post_id);
}
?>
<h2>Modifier un thème</h2>
<p><input type="text" name="libelle" value="<?php echo $info->getLibelle(); ?>" placeholder="Saisissez le libelle" required></p>
<p><input type="hidden" name="id" value="<?php echo $info->getId(); ?>" required></p>
<p><input type="submit" name="Modifier" value="Enregistrer"></p>
<p><a href="?p=gerer_theme&action=supprimer&id=<?php echo $id; ?>">Supprimer</a></p>