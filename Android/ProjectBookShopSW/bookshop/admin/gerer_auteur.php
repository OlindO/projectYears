<form action="#" method="post">
<?php
switch($actionApp){
	case "lister":
		include_once("auteur/lister.php");
		break;
	case "ajouter":
		include_once("auteur/ajouter.php");
		break;
	case "modifier":
		include_once("auteur/modifier.php");
		break;
	case "supprimer":
		include_once("auteur/supprimer.php");
		break;
}
?>
</form>
