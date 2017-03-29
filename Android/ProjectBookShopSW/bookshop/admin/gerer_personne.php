<form action="#" method="post" enctype="multipart/form-data">
<?php
switch($actionApp){
	case "lister":
		include_once("personne/lister.php");
		break;
	case "ajouter":
		include_once("personne/ajouter.php");
		break;
	case "modifier":
		include_once("personne/modifier.php");
		break;
	case "supprimer":
		include_once("personne/supprimer.php");
		break;
	case "connexion":
		include_once("personne/connexion.php");
		break;
}
?>
</form>