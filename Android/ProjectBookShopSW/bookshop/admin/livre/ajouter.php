<?php
if(isset($_POST["Ajouter"])){
	// Traitement de l'ajout
	if(isset($_POST["Ajouter"])){
		$post_titre = str_ireplace("'", "''", $_POST["titre"]);
		$post_description = str_ireplace("'", "''", $_POST["description"]);
		$post_prix = str_ireplace("'", "''", $_POST["prix"]);
		$post_isbn = str_ireplace("'", "''", $_POST["isbn"]);
		$post_photo = str_ireplace("'", "''", $_POST["photo"]);
		$tabAuteursID = $_POST["liste_auteur"];
		$tabThemesID = $_POST["liste_theme"];
		$post_quantite = str_ireplace("'", "''", $_POST["quantite"]);
		//--
		$livre = new Livre();
		$livre->setTitre($post_titre);
		$livre->setDescription($post_description);
		$livre->setPrix($post_prix);
		$livre->setIsbn($post_isbn);
		$livre->setPhoto($post_photo);
		
		//-- Ajout du livre
		$daoLivre = new DAOLivre();
		$livreInsertId = $daoLivre->ajouter($livre);
		$objInsertLivre = DAOLivre::getById($livreInsertId);
		//--
		if($livreInsertId){
			$cmptLivreAuteur=0;
			//--
			for($i=0; $i<count($tabAuteursID); $i++){
				$auteur_id = $tabAuteursID[$i];
				$livreAuteur = new LivreAuteur();
				$daoAuteur = DAOAuteur::getById($auteur_id);
				$livreAuteur->setAuteur($daoAuteur);
				$livreAuteur->setLivre($objInsertLivre);
				//--
				// Ajout dans livre_Auteur
				$daoLivreAuteur = new DAOLivreAuteur();
				$livreAuteurInsertId = $daoLivreAuteur->ajouter($livreAuteur);
				if($livreAuteurInsertId){
					$cmptLivreAuteur++;
				}
			}

			//-- Ajout dans livre_Theme
			$cmptLivreTheme = 0;
			for($i=0; $i<count($tabThemesID); $i++){
				$theme_id = $tabThemesID[$i];
				//--
				$livreTheme = new LivreTheme();
				$daoTheme = DAOTheme::getById($theme_id);
				$livreTheme->setTheme($daoTheme);
				$livreTheme->setLivre($objInsertLivre);
				//--
				// Ajout dans livre_Theme
				$daoLivreTheme = new DAOLivreTheme();
				$livreThemeInsertId = $daoLivreTheme->ajouter($livreTheme);
				if($livreThemeInsertId){
					$cmptLivreTheme++;
				}
			}

			//-- Ajout dans le stock
			$stock = new Stock();
			$stock->setLivre($objInsertLivre);
			$stock->setQuantite($post_quantite);
			$daoStock = new DAOStock();
			$stockInsertId = $daoStock->ajouter($stock);
			
			//--
			if($stockInsertId)
				echo "<h4 class='succes'>Livre ajouté avec succès<h4>";
			else
				echo "<h4 class='erreur'>Livre non ajouté<h4>";
		}
		else{
			echo "<h4 class='erreur'>Livre non ajouté<h4>";
		}
	}
}
?>
<h2>Ajouter un livre</h2>
<p><input type="text" name="titre" placeholder="Titre" required></p>
<p><textarea name="description" placeholder="Description" required></textarea></p>
<p><input type="text" name="prix" placeholder="Prix" required></p>
<p><input type="text" name="isbn" placeholder="ISBN" required></p>
<p><input type="text" name="photo" placeholder="Url de la photo" required></p>
<p><input type="number" name="quantite" placeholder="Quantité en stock" required></p>
<h4>Sélectionnez un ou plusieurs auteurs</h4>
<?php
$daoAuteur = new DAOAuteur();
$resultat = $daoAuteur->lister();
foreach ($resultat as $r){
	?>
	<input type="checkbox" name="liste_auteur[]" value="<?php echo $r->getId(); ?>"> <?php echo $r->getPrenom()." ".$r->getNom(); ?>
	<?php 
}
?>
<h4>Sélectionnez un ou plusieurs thèmes</h4>
<?php
$daoTheme = new DAOTheme();
$resultat = $daoTheme->lister();
foreach ($resultat as $r){
	?>
	<input type="checkbox" name="liste_theme[]" value="<?php echo $r->getId(); ?>"> <?php echo $r->getLibelle(); ?>
	<?php 
}
?>
<p><input type="submit" name="Ajouter" value="Ajouter un nouveau livre"></p>