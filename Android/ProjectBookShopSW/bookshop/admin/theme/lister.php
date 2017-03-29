<h2>Liste des thèmes</h2>
<?php
$daoTheme = new DAOTheme();
$resultat = $daoTheme->lister();
foreach ($resultat as $r){
	?>
	<a class="liste" href="?p=gerer_theme&action=modifier&id=<?php echo $r->getId(); ?>"><?php echo $r->getId()." : ".$r->getLibelle(); ?></a>
	<?php 
}
//echo "<pre>"; print_r($resultat); echo "</pre>";
?>