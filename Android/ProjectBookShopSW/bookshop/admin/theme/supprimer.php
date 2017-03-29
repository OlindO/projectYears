<?php
$id = isset($_GET["id"]) ? $_GET["id"] : 0;
if(empty($id)) exit("Erreur...");
//--
if(isset($_GET["confirm"])){
	$daoTheme = new DAOTheme();
	$resultat = $daoTheme->supprimer($id);
	if($resultat){
		echo "<h4 class='succes'>Thème supprimé avec succès</h4>";
		exit("");
	}
	else{
		echo "<h4 class='erreur'>Thème non supprimé</h4>";
	}
}
?>
<h2>Confirmation de suppression</h2>
<p>Voulez-vous vraiment supprimer ce thème ?</p>

<p><a href="?p=gerer_theme&action=lister">ANNULER</a> | <a href="?p=gerer_theme&action=supprimer&id=<?php echo $id; ?>&confirm=oui">OUI</a></p>