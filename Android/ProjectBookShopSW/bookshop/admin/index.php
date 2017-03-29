<?php
require_once("../classes/bibliotheque.php");
require_once("../dao/dao-auteur.php");
require_once("../dao/dao-theme.php");
require_once("../dao/dao-stock.php");
require_once("../dao/dao-livre.php");
require_once("../dao/dao-livre-auteur.php");
require_once("../dao/dao-livre-theme.php");
require_once("../dao/dao-personne.php");
//--
$pageApp = isset($_GET["p"]) ? $_GET["p"] : "gerer_auteur";
$actionApp = isset($_GET["action"]) ? $_GET["action"] : "lister";
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN::BOOKSHOP</title>
<link rel="stylesheet" href="index.css">
</head>
<body>

<table style="width: 100%;">
	<tr>
		<td width="300">
		<form action="">
			<fieldset>
				<legend>Gérer les auteurs</legend>
				<div>
					<a class="menu" href="?p=gerer_auteur&action=lister">LISTER LES AUTEURS</a>
					<a class="menu" href="?p=gerer_auteur&action=ajouter">AJOUTER UN AUTEUR</a>
				</div>
			</fieldset>
			<fieldset>
				<legend>Gérer les thèmes</legend>
				<div>
					<a class="menu" href="?p=gerer_theme&action=lister">LISTER DES THEMES</a>
					<a class="menu" href="?p=gerer_theme&action=ajouter">AJOUTER UN THEMES</a>
				</div>
			</fieldset>
			<fieldset>
				<legend>Gérer les livres</legend>
				<div>
					<a class="menu" href="?p=gerer_livre&action=lister">LISTER LES LIVRES</a>
					<a class="menu" href="?p=gerer_livre&action=ajouter">AJOUTER UN LIVRE</a>
				</div>
			</fieldset>
			<fieldset>
				<legend>Gérer les personnes</legend>
				<div>
					<a class="menu" href="?p=gerer_personne&action=lister">LISTER LES PERSONNES</a>
					<a class="menu" href="?p=gerer_personne&action=ajouter">AJOUTER UNE PERSONNE</a>
					<a class="menu" href="?p=gerer_personne&action=connexion">CONNECTER UNE PERSONNE</a>
				</div>
			</fieldset>
		</form>
		</td>
		<td valign="top">
		<?php 
			$filename = $pageApp.".php";
			if(file_exists($filename)){
				include_once($filename);
			}
			else{
				exit("Erreur");
			}
		?>
		</td>
	</tr>
</table>

</body>
</html>