<?php
$id = isset($_GET["id"]) ? $_GET["id"] : 0;
if(empty($id)) exit();
$infosLivre = DAOLivre::getById($id);
if(!$infosLivre) exit();
$daoLivreAuteur = new DAOLivreAuteur();
$tabNomPrenom = explode(", ", DAOLivreAuteur::getNomPrenomAuteurByLivreId($id));
$tabLibelle = explode(", ", DAOLivreTheme::getLibelleThemeByLivreId($id));
$qteStock = DAOStock::getQuantiteByLivreId($id);
//--
// Traitement de la modification
if(isset($_POST["Modifier"])){
	
	
	$post_id = str_ireplace("'", "''", $_POST["id"]);
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
	$livre->setId($post_id);
	$livre->setTitre($post_titre);
	$livre->setDescription($post_description);
	$livre->setPrix($post_prix);
	$livre->setIsbn($post_isbn);
	$livre->setPhoto($post_photo);

	//-- Modification du livre
	$daoLivre = new DAOLivre();
	$resultat = $daoLivre->modifier($livre);
	$objInsertLivre = DAOLivre::getById($post_id);
	//--
	if($resultat){
		//-- Suppression dans livre_Auteur
		$resultatSupp = DAOLivreAuteur::supprimerByLivreId($post_id);
		if($resultatSupp){
			//-- Ajout dans livre_Auteur
			$cmptLivreAuteur=0;
			for($i=0; $i<count($tabAuteursID); $i++){
				$auteur_id = $tabAuteursID[$i];
				$livreAuteur = new LivreAuteur();
				$daoAuteur = DAOAuteur::getById($auteur_id);
				$livreAuteur->setAuteur($daoAuteur);
				$livreAuteur->setLivre($objInsertLivre);
				//--
				$daoLivreAuteur = new DAOLivreAuteur();
				$livreAuteurInsertId = $daoLivreAuteur->ajouter($livreAuteur);
				if($livreAuteurInsertId){
					$cmptLivreAuteur++;
				}
			}
		}

		//-- Suppression dans livre_Theme
		$resultatSupp = DAOLivreTheme::supprimerByLivreId($post_id);
		if($resultatSupp){
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
				$daoLivreTheme = new DAOLivreTheme();
				$livreThemeInsertId = $daoLivreTheme->ajouter($livreTheme);
				if($livreThemeInsertId){
					$cmptLivreTheme++;
				}
			}
		}

		//-- Ajout dans le stock
		exit($post_quantite);
		$stock = new Stock();
		$stock->setLivre($objInsertLivre);
		$stock->setQuantite($post_quantite);
		$daoStock = new DAOStock();
		$stockInsertId = $daoStock->modifier($stock);
		//--
		if($stockInsertId)
			echo "<h4 class='succes'>Livre modifié avec succès<h4>";
		else
			echo "<h4 class='erreur'>Livre non modifié<h4>";
	}
	else{
		echo "<h4 class='erreur'>Livre non modifié<h4>";
	}
	//--
	$infosLivre = DAOLivre::getById($post_id);
}
?>
<h2>Modifier un livre</h2>
<p>TITRE: <input type="text" name="titre" value="<?php echo $infosLivre->getTitre(); ?>" placeholder="Titre" required></p>
<p>DESC: <textarea name="description" placeholder="Description" required><?php echo $infosLivre->getDescription(); ?></textarea></p>
<p>PRIX: <input type="text" name="prix" value="<?php echo $infosLivre->getPrix(); ?>" placeholder="Prix" required></p>
<p>ISBN: <input type="text" name="isbn"  value="<?php echo $infosLivre->getIsbn(); ?>" placeholder="ISBN" required></p>
<p>PHOTO: <input type="text" name="photo" value="<?php echo $infosLivre->getPhoto(); ?>" placeholder="Url de la photo" required></p>
<p>QTE: <input type="number" name="quantite" value="<?php echo $qteStock; ?>" placeholder="Quantité en stock" required></p>
<h4>Sélectionnez un ou plusieurs auteurs</h4>
<?php
$daoAuteur = new DAOAuteur();
$resultat = $daoAuteur->lister();
foreach ($resultat as $r){
	$prenomNom = $r->getPrenom()." ".$r->getNom();
	?>
	<input <?php echo (in_array($prenomNom, $tabNomPrenom, TRUE) ? 'checked="checked"' : ''); ?> type="checkbox" name="liste_auteur[]" value="<?php echo $r->getId(); ?>"> <?php echo $prenomNom; ?>
	<?php 
}
?>
<h4>Sélectionnez un ou plusieurs thèmes</h4>
<?php
$daoTheme = new DAOTheme();
$resultat = $daoTheme->lister();
foreach ($resultat as $r){
	$libelle = $r->getLibelle();
	?>
	<input <?php echo (in_array($libelle, $tabLibelle, TRUE) ? 'checked="checked"' : ''); ?> type="checkbox" name="liste_theme[]" value="<?php echo $r->getId(); ?>"> <?php echo $libelle; ?>
	<?php 
}
?>
<p><input type="submit" name="Modifier" value="Enregistrer la modification"></p>
<p><input type="hidden" name="id" value="<?php echo $id; ?>"></p>