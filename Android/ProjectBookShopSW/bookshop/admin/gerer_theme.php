<form action="#" method="post">
<?php
switch($actionApp){
	case "lister":
		include_once("theme/lister.php");
		break;
	case "ajouter":
		include_once("theme/ajouter.php");
		break;
	case "modifier":
		include_once("theme/modifier.php");
		break;
	case "supprimer":
		include_once("theme/supprimer.php");
		break;
}
?>
</form>
<?php 

// Ajouter Theme ################################################

/*
$theme = new Theme();
$theme->setTitre("Comment trouver le salut");
$theme->setDescription("Allons et cherchons à sauver notre ame");
$theme->setPrix("25.50");
$theme->setIsbn("8975462");
$theme->setPhoto("salut.jpg");
$daoTheme = new DAOTheme();
$resultat = $daoTheme->ajouter($theme);
// Ajout du theme
if($resultat){ 
	//echo "$resultat : Theme ajouté avec succès";
	// Ajout dans la table theme_auteur : Soit auteur_id = 1 et 2
	$tabAuteursID = array(1, 2);
	for($i=0; $i<count($tabAuteursID); $i++){
		$auteur_id = $tabAuteursID[$i];
		$themeAuteur = new ThemeAuteur();
		$daoAuteur = DAOAuteur::getById($auteur_id);
		$themeAuteur->setAuteur($daoAuteur);
		$daoTheme = DAOTheme::getById($resultat);
		$themeAuteur->setTheme($daoTheme);
		//--
		//echo "<pre>"; print_r($daoTheme); echo "</pre>";
		$daoThemeAuteur = new DAOThemeAuteur();
		$resultat = $daoThemeAuteur->ajouter($themeAuteur);
		if($resultat){ 
			echo "$resultat : ThemeAuteur ajouté avec succès";
		}
		else{
			echo "$resultat : ThemeAuteur non ajouté";
		}
	}
}
else{
	echo "$resultat : Theme non ajouté";
}
*/


// Modifier Theme ################################################
/*
$id = 1;
$theme = new Theme();
$theme->setId($id);
$theme->setTitre("Titre");
$theme->setDescription("Description");
$theme->setPrix("0.50");
$theme->setIsbn("52545685");
$theme->setPhoto("okookok.jpg");
$daoTheme = new DAOTheme();
$resultat = $daoTheme->modifier($theme);
if($resultat){
	echo "Theme modifié avec succès";
}
else{
	echo "Theme non modifié";
}
*/

// Lister Theme ################################################

/*
$daoTheme = new DAOTheme();
$resultat = $daoTheme->lister();
echo "<pre>"; print_r($resultat); echo "</pre>";
*/

// Infos Theme ################################################
/*
$id = 3;
$resultat = DAOTheme::getById($id);
echo "<pre>"; print_r($resultat); echo "</pre>";
*/

// Supprimer Theme ################################################
/*
$id = 1;
$daoTheme = new DAOTheme();
$resultat = $daoTheme->supprimer($id);
if($resultat){
	echo "Theme supprimé avec succès";
}
else{
	echo "Theme non supprimé";
}
*/
