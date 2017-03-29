<?php
require_once("db.php");
require_once("../classes/stock.php");
require_once("../interfaces/interface-stock.php");

class DAOStock implements IStock
{
	public function ajouter($stock)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_stock (id,quantite,livre_id) VALUES (NULL, '".$stock->getQuantite()."', '".$stock->getLivre()->getId()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_stock ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$stock = new Stock();
			$stock->setId($resultat->id);
			$stock->setQuantite($resultat->quantite);
			$stock->setLivre($resultat->livre_id);
			$stock->setDate($resultat->date);
			$infos[] = $stock;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_stock WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$stock = new Stock();
			$stock->setId($resultat->id);
			$stock->setQuantite($resultat->quantite);
			$stock->setLivre($resultat->livre_id);
			$stock->setDate($resultat->date);
			$info = $stock;
		}
		$resultats->closeCursor();
		return $info;
	}
	
	public static function getQuantiteByLivreId($livre_id)
	{
		$info="";
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select quantite FROM t_stock WHERE livre_id = '$livre_id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$info = $resultat->quantite;
		}
		$resultats->closeCursor();
		return $info;
	}

	public function modifier($stock)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_stock SET quantite = '".$stock->getQuantite()."', livre_id = '".$stock->getLivre()->getId()."' WHERE id = '".$stock->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_stock WHERE id = '$id'");
		return $resultat;
	}
}
