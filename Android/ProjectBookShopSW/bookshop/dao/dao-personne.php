<?php
require_once("db.php");
require_once("../classes/personne.php");
require_once("../interfaces/interface-personne.php");

class DAOPersonne implements IPersonne
{
	public function ajouter($personne)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_personne (id, nom, prenom, email, password, civilite, photo) VALUES (NULL, '".$personne->getNom()."', '".$personne->getPrenom()."', '".$personne->getEmail()."', '".$personne->getPassword()."', '".$personne->getCivilite()."', '".$personne->getPhoto()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_personne ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$personne = new Personne();
			$personne->setId($resultat->id);
			$personne->setNom($resultat->nom);
			$personne->setPrenom($resultat->prenom);
			$personne->setEmail($resultat->email);
			$personne->setPassword($resultat->password);
			$personne->setCivilite($resultat->civilite);
			$personne->setPhoto($resultat->photo);
			$infos[] = $personne;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_personne WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$personne = new Personne();
			$personne->setId($resultat->id);
			$personne->setNom($resultat->nom);
			$personne->setPrenom($resultat->prenom);
			$personne->setEmail($resultat->email);
			$personne->setPassword($resultat->password);
			$personne->setCivilite($resultat->civilite);
			$personne->setPhoto($resultat->photo);
			$info = $personne;
		}
		$resultats->closeCursor();
		return $info;
	}
	
	public function getPersonneBy($email, $password){
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_personne WHERE email LIKE '$email' AND password LIKE '$password'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$personne = new Personne();
			$personne->setId($resultat->id);
			$personne->setNom($resultat->nom);
			$personne->setPrenom($resultat->prenom);
			$personne->setEmail($resultat->email);
			$personne->setPassword($resultat->password);
			$personne->setCivilite($resultat->civilite);
			$personne->setPhoto($resultat->photo);
			$info = $personne;
		}
		$resultats->closeCursor();
		return $info;
	}

	public function modifier($personne)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_personne SET nom='".$personne->getNom()."', prenom='".$personne->getPrenom()."', email='".$personne->getEmail()."', password='".$personne->getPassword()."', civilite='".$personne->getCivilite()."', photo='".$personne->getPhoto()."' WHERE id = '".$personne->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_personne WHERE id = '$id'");
		return $resultat;
	}
}
