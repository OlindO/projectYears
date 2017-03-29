<?php
$id = isset($_GET["id"]) ? $_GET["id"] : 0;
if(empty($id)) exit("Erreur...");
//--
if(isset($_GET["confirm"])){
	$daoPersonne = new DAOPersonne();
	$resultat = $daoPersonne->supprimer($id);
	if($resultat){
		echo "<h4 class='succes'>Personne supprimée avec succès</h4>";
		exit("");
	}
	else{
		echo "<h4 class='erreur'>Personne non supprimée</h4>";
	}
}
?>
<h2>Confirmation de suppression</h2>
<p>Voulez-vous vraiment supprimer cette personne ?</p>

<p><a href="?p=gerer_personne&action=lister">ANNULER</a> | <a href="?p=gerer_personne&action=supprimer&id=<?php echo $id; ?>&confirm=oui">OUI</a></p>