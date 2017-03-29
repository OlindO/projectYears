<?php
if(isset($_POST["Ajouter"])){
	$post_theme = str_ireplace("'", "''", $_POST["theme"]);
	//--
	$theme = new Theme();
	$theme->setLibelle($post_theme);
	$daoTheme = new DAOTheme();
	$resultat = $daoTheme->ajouter($theme);
	if($resultat){
		echo "<h4 class='succes'>$resultat : Theme ajouté avec succès<h4>";
	}
	else{
		echo "<h4 class='erreur'>$resultat : Theme non ajouté<h4>";
	}
}
?>
<h2>Ajouter un thème</h2>
<p><input type="text" name="theme" placeholder="Saisissez le theme" required></p>
<p><input type="submit" name="Ajouter" value="Ajouter le thème"></p>