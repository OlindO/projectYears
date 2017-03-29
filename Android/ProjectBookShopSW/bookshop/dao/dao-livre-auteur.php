<?php
require_once("db.php");
require_once("../classes/livre-auteur.php");
require_once("../interfaces/interface-livre-auteur.php");

class DAOLivreAuteur implements ILivreAuteur
{
	public function ajouter($livreAuteur)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_livre_auteur (id,livre_id,auteur_id) VALUES (NULL, '".$livreAuteur->getLivre()->getId()."', '".$livreAuteur->getAuteur()->getId()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_livre_auteur ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$livreAuteur = new LivreAuteur();
			$livreAuteur->setId($resultat->id);
			$livreAuteur->setLivre(DAOLivre::getIdBy($resultat->livre_id));
			$livreAuteur->setAuteur(DAOAuteur::getIdBy($resultat->auteur_id));
			$infos[] = $livreAuteur;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_livre_auteur WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$livreAuteur = new LivreAuteur();
			$livreAuteur->setId($resultat->id);
			$livreAuteur->setLivre(DAOLivre::getIdBy($resultat->livre_id));
			$livreAuteur->setAuteur(DAOAuteur::getIdBy($resultat->auteur_id));
			$info = $livreAuteur;
		}
		$resultats->closeCursor();
		return $info;
	}
	
	public static function getNomPrenomAuteurByLivreId($livre_id)
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select nom, prenom FROM t_livre_auteur as tla, t_livre as tl, t_auteur as ta WHERE tla.livre_id = tl.id AND ta.id = tla.auteur_id AND tl.id = $livre_id");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$infos[] = $resultat->prenom." ".$resultat->nom;
		}
		$resultats->closeCursor();
		return implode(", ", $infos);
	}
	

	public function modifier($livreAuteur)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_livre_auteur SET livre_id = '".$livreAuteur->getLivre()->getId()."', auteur_id = '".$livreAuteur->getAuteur()->getId()."' WHERE id = '".$livreAuteur->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_livre_auteur WHERE id = '$id'");
		return $resultat;
	}
	
	public static function supprimerByLivreId($livre_id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_livre_auteur WHERE livre_id = '$livre_id'");
		return $resultat;
	}
}
