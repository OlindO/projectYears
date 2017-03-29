<?php
require_once("db.php");
require_once("../classes/livre.php");
require_once("../interfaces/interface-livre.php");

class DAOLivre implements ILivre
{
	public function ajouter($livre)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_livre (id, titre, description, prix, isbn, photo) VALUES (NULL, '".$livre->getTitre()."', '".$livre->getDescription()."', '".$livre->getPrix()."', '".$livre->getIsbn()."', '".$livre->getPhoto()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_livre ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$livre = new Livre();
			$livre->setId($resultat->id);
			$livre->setTitre($resultat->titre);
			$livre->setDescription($resultat->description);
			$livre->setPrix($resultat->prix);
			$livre->setIsbn($resultat->isbn);
			$livre->setPhoto($resultat->photo);
			$infos[] = $livre;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_livre WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$livre = new Livre();
			$livre->setId($resultat->id);
			$livre->setTitre($resultat->titre);
			$livre->setDescription($resultat->description);
			$livre->setPrix($resultat->prix);
			$livre->setIsbn($resultat->isbn);
			$livre->setPhoto($resultat->photo);
			$info = $livre;
		}
		$resultats->closeCursor();
		return $info;
	}

	public function modifier($livre)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_livre SET titre='".$livre->getTitre()."', description='".$livre->getDescription()."', prix='".$livre->getPrix()."', isbn='".$livre->getIsbn()."', photo='".$livre->getPhoto()."' WHERE id = '".$livre->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_livre WHERE id = '$id'");
		return $resultat;
	}
}
