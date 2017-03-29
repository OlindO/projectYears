<?php
$id = isset($_GET["id"]) ? $_GET["id"] : 0;
$info = DAOPersonne::getById($id);
if(!$info) exit("Aucune personne trouvée");

if(isset($_POST["Modifier"])){
	$post_photo_old = $_POST["photo_old"];
	
	// Traitement de la photo
	if(isset($_FILES["photo"])){
	
		$name= date("YmdHis")."-".$_FILES["photo"]["name"];
		$type= $_FILES["photo"]["type"];
		$size= $_FILES["photo"]["size"];
		$temp= $_FILES["photo"]["tmp_name"];
		$error= $_FILES["photo"]["error"];
	
		if($error == 0)
		{
			$photo = Bibliotheque::removeaccents($name);
			move_uploaded_file($temp, "../upload/".$photo);
			// Suppression
			$filename = "../upload/".$post_photo_old;
			if(file_exists($filename)){
				unlink($filename);
			}
		}
		else{
			$photo = $post_photo_old;
		}
	}
	
	$post_id = str_ireplace("'", "''", $_POST["id"]);
	$civilite = str_ireplace("'", "''", $_POST["civilite"]);
	$nom = str_ireplace("'", "''", $_POST["nom"]);
	$prenom = str_ireplace("'", "''", $_POST["prenom"]);
	$email = str_ireplace("'", "''", $_POST["email"]);
	$password = str_ireplace("'", "''", $_POST["password"]);
	//--
	$personne = new Personne();
	$personne->setId($post_id);
	$personne->setNom($nom);
	$personne->setPrenom($prenom);
	$personne->setEmail($email);
	$personne->setPassword($password);
	$personne->setCivilite($civilite);
	$personne->setPhoto($photo);
	$daoPersonne = new DAOPersonne();
	$resultat = $daoPersonne->modifier($personne);
	if($resultat){
		echo "<h4 class='succes'>$resultat : Personne modifiée avec succès<h4>";
	}
	else{
		echo "<h4 class='erreur'>$resultat : Personne non modifiée<h4>";
	}
	//--
	$info = DAOPersonne::getById($post_id);
}
?>
<h2>Modifier une personne</h2>
<?php 
if(strlen($info->getPhoto()) >= 10){
	?><img width="60" src="../upload/<?php echo $info->getPhoto(); ?>"><?php 
}
//--
$civilite = $info->getCivilite();
?>
<p>
	<select name="civilite" required>
		<option <?php echo ($civilite == "Madame" ? 'selected="selected"' : ''); ?> value="Madame">Madame</option>
		<option <?php echo ($civilite == "Mademoiselle" ? 'selected="selected"' : ''); ?> selected="selected" value="Mademoiselle">Mademoiselle</option>
		<option <?php echo ($civilite == "Monsieur" ? 'selected="selected"' : ''); ?> selected="selected" value="Monsieur">Monsieur</option>
	</select>
</p>
<p><input type="text" name="nom" value="<?php echo $info->getNom(); ?>" placeholder="Votre nom" required></p>
<p><input type="text" name="prenom" value="<?php echo $info->getPrenom(); ?>" placeholder="Votre prénom" required></p>
<p><input type="email" name="email" value="<?php echo $info->getEmail(); ?>" placeholder="Votre adresse email" required></p>
<p><input type="hidden" name="password" value="<?php echo $info->getPassword(); ?>" placeholder="Votre mot de passe" required></p>
<p><input type="file" name="photo" value="" placeholder="Photo"></p>
<p><input type="submit" name="Modifier" value="Enregistrer"></p>
<p><a href="?p=gerer_personne&action=supprimer&id=<?php echo $id; ?>">Supprimer</a></p>
<p><input type="hidden" name="id" value="<?php echo $id; ?>" required></p>
<p><input type="hidden" name="photo_old" value="<?php echo $info->getPhoto(); ?>" required></p>

