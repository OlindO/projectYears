<?php
$arborescence = array("", "../", "../../", "../../../", "../../../../", "../../../../../");
for($i=0; $i<count($arborescence); $i++){
	$filename = $arborescence[$i]."dao/dao-personne.php";
	if(file_exists($filename)){
		require_once($filename);
	}
}

$action = isset($_GET["action"]) ? $_GET["action"] : "";
$filename = $action.".php";
if(file_exists("personne/".$filename)){
	include_once("personne/".$filename);
}
else{
	exit("Erreur... ?action=ACTION");
	?>
<!--  
	<form action="http://10.75.25.189:8080/bookshop/json/personne.php?action=connexion" method="post">
		<p><input type="email" name="email" placeholder="Adresse email"><input type="password" name="password" placeholder="Mot de passe"></p>
		<input type="submit" name="btnEnvoyer" value="Envoyer">
	</form>
-->
	<?php 
}
?>