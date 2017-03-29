<?php
$id = isset($_GET["id"]) ? $_GET["id"] : 0;
if(empty($id)) exit("Erreur...");
//--
if(isset($_GET["confirm"])){
	$daoAuteur = new DAOAuteur();
	$resultat = $daoAuteur->supprimer($id);
	if($resultat){
		echo "<h4 class='succes'>Auteur supprimé avec succès</h4>";
		exit("");
	}
	else{
		echo "<h4 class='erreur'>Auteur non supprimé</h4>";
	}
}
?>
<h2>Confirmation de suppression</h2>
<p>Voulez-vous vraiment supprimer cet auteur ?</p>

<p><a href="?p=gerer_auteur&action=lister">ANNULER</a> | <a href="?p=gerer_auteur&action=supprimer&id=<?php echo $id; ?>&confirm=oui">OUI</a></p>