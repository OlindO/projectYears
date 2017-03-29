<?php
require_once("db.php");
require_once("../classes/livre-theme.php");
require_once("../interfaces/interface-livre-theme.php");

class DAOLivreTheme implements ILivreTheme
{
	public function ajouter($livreTheme)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_livre_theme (id,livre_id,theme_id) VALUES (NULL, '".$livreTheme->getLivre()->getId()."', '".$livreTheme->getTheme()->getId()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_livre_theme ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$livreTheme = new LivreTheme();
			$livreTheme->setId($resultat->id);
			$livreTheme->setLivre(DAOLivre::getIdBy($resultat->livre_id));
			$livreTheme->setTheme(DAOTheme::getIdBy($resultat->theme_id));
			$infos[] = $livreTheme;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_livre_theme WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$livreTheme = new LivreTheme();
			$livreTheme->setId($resultat->id);
			$livreTheme->setLivre(DAOLivre::getIdBy($resultat->livre_id));
			$livreTheme->setTheme(DAOTheme::getIdBy($resultat->theme_id));
			$info = $livreTheme;
		}
		$resultats->closeCursor();
		return $info;
	}
	
	public static function getLibelleThemeByLivreId($livre_id)
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select libelle FROM t_livre_theme as tlt, t_livre as tl, t_theme as tt WHERE tlt.livre_id = tl.id AND tt.id = tlt.theme_id AND tl.id = $livre_id");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$infos[] = $resultat->libelle;
		}
		$resultats->closeCursor();
		return implode(", ", $infos);
	}

	public function modifier($livreTheme)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_livre_theme SET livre_id = '".$livreTheme->getLivre()->getId()."', theme_id = '".$livreTheme->getTheme()->getId()."' WHERE id = '".$livreTheme->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_livre_theme WHERE id = '$id'");
		return $resultat;
	}
	
	public static function supprimerByLivreId($livre_id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_livre_theme WHERE livre_id = '$livre_id'");
		return $resultat;
	}
	
}
