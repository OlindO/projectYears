<h2>Liste des auteurs</h2>
<?php
$daoAuteur = new DAOAuteur();
$resultat = $daoAuteur->lister();
foreach ($resultat as $r){
	?>
	<a class="liste" href="?p=gerer_auteur&action=modifier&id=<?php echo $r->getId(); ?>"><?php echo $r->getId()." : ".$r->getPrenom()." ".$r->getNom(); ?></a>
	<?php 
}
//echo "<pre>"; print_r($resultat); echo "</pre>";
?>