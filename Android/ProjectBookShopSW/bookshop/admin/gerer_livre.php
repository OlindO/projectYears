<form action="#" method="post">
<?php
switch($actionApp){
	case "lister":
		include_once("livre/lister.php");
		break;
	case "ajouter":
		include_once("livre/ajouter.php");
		break;
	case "modifier":
		include_once("livre/modifier.php");
		break;
	case "supprimer":
		include_once("livre/supprimer.php");
		break;
}
?>
</form>
