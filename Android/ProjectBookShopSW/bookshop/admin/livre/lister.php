<h2>Liste des livres</h2>
<table border=1>
	<tr>
		<th>TITRE</th><th>PRIX</th><th>THEMES</th><th>AUTEURS</th><th>PHOTO</th><th>ACTION</th>
	</tr>
	<?php
	$daoLivre = new DAOLivre();
	$livres = $daoLivre->lister();
	//echo "<pre>"; print_r($livres); echo "</pre>";
	foreach ($livres as $l){
		$daoLivreAuteur = new DAOLivreAuteur();
		$nomPrenom = DAOLivreAuteur::getNomPrenomAuteurByLivreId($l->getId());
		$libelle = DAOLivreTheme::getLibelleThemeByLivreId($l->getId());
		?>
		<tr>
			<td><?php echo $l->getTitre(); ?></td>
			<td><?php echo $l->getPrix(); ?> €</td>
			<td><?php echo $libelle; ?></td>
			<td><?php echo $nomPrenom; ?></td>
			<td><img width="60" src="<?php echo $l->getPhoto(); ?>"></td>
			<td><a href="?p=gerer_livre&action=modifier&id=<?php echo $l->getId(); ?>">Modifier</a> | <a href="?p=gerer_livre&action=supprimer&id=<?php echo $l->getId(); ?>">Supprimer</a></td>
		</tr>
		<?php 
	}
	?>
</table>



