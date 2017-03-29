<?php
require_once("db.php");
require_once("../classes/commande.php");
require_once("../interfaces/interface-commande.php");

class DAOCommande
{
	public function ajouter($commande)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_commande (id, numero, montant, personne_id, date) VALUES (NULL, '".$commande->getNumero()."', '".$commande->getMontant()."', '".$commande->getDate()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_commande ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$commande = new Commande();
			$commande->setId($resultat->id);
			$commande->setNumero($resultat->numero);
			$commande->setMontant($resultat->montant);
			$commande->setPersonne(Personne::getById($resultat->personne_id));
			$commande->setDate($resultat->date);
			$infos[] = $commande;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_commande WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$commande = new Commande();
			$commande->setId($resultat->id);
			$commande->setNumero($resultat->numero);
			$commande->setMontant($resultat->montant);
			$commande->setPersonne(Personne::getById($resultat->personne_id));
			$commande->setDate($resultat->date);
			$info = $commande;
		}
		$resultats->closeCursor();
		return $info;
	}

	public function modifier($commande)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_commande SET numero = '".$commande->getNumero()."', montant = '".$commande->getMontant()."', personne_id = '".$commande->getPersonne()->getId()."' WHERE id = '".$commande->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_commande WHERE id = '$id'");
		return $resultat;
	}
}
