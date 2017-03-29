<?php
require_once("db.php");
require_once("../classes/auteur.php");
require_once("../interfaces/interface-auteur.php");

class DAOAuteur implements IAuteur
{
	public function ajouter($auteur)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_auteur (id,nom,prenom) VALUES (NULL, '".$auteur->getNom()."', '".$auteur->getPrenom()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_auteur ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$auteur = new Auteur();
			$auteur->setId($resultat->id);
			$auteur->setNom($resultat->nom);
			$auteur->setPrenom($resultat->prenom);
			$infos[] = $auteur;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_auteur WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$auteur = new Auteur();
			$auteur->setId($resultat->id);
			$auteur->setNom($resultat->nom);
			$auteur->setPrenom($resultat->prenom);
			$info = $auteur;
		}
		$resultats->closeCursor();
		return $info;
	}

	public function modifier($auteur)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_auteur SET nom = '".$auteur->getNom()."', prenom = '".$auteur->getPrenom()."' WHERE id = '".$auteur->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_auteur WHERE id = '$id'");
		return $resultat;
	}
}
