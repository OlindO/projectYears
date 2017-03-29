<h2>Liste des personnes</h2>
<table border=1>
	<tr>
		<th>CIVILITE</th><th>NOM</th><th>PRENOM</th><th>EMAIL</th><th>PHOTO</th><th>ACTION</th>
	</tr>
	<?php
	$daoPersonne = new DAOPersonne();
	$resultat = $daoPersonne->lister();
	foreach ($resultat as $r){
		?>
		<tr>
			<td><?php echo $r->getCivilite(); ?></td>
			<td><?php echo $r->getNom(); ?></td>
			<td><?php echo $r->getPrenom(); ?></td>
			<td><?php echo $r->getEmail(); ?></td>
			<td>
				<?php 
				if(strlen($r->getPhoto()) >= 10){
					?><img width="60" src="../upload/<?php echo $r->getPhoto(); ?>"><?php 
				}
				?>
			</td>
			<td><a href="?p=gerer_personne&action=modifier&id=<?php echo $r->getId(); ?>">Modifier</a> | <a href="?p=gerer_personne&action=supprimer&id=<?php echo $r->getId(); ?>">Supprimer</a></td>
		</tr>
		<?php 
	}
	?>
</table>



