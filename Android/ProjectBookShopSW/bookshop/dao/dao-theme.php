<?php
require_once("db.php");
require_once("../classes/theme.php");
require_once("../interfaces/interface-theme.php");

class DAOTheme
{
	public function ajouter($theme)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$connexion->exec("INSERT INTO t_theme (id, libelle) VALUES (NULL, '".$theme->getLibelle()."')");
		$lastID = $connexion->lastInsertId();
		return $lastID;
	}

	public function lister()
	{
		$infos = array();
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_theme ORDER BY id DESC");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$theme = new Theme();
			$theme->setId($resultat->id);
			$theme->setLibelle($resultat->libelle);
			$infos[] = $theme;
		}
		$resultats->closeCursor();
		return $infos;
	}

	public static function getById($id)
	{
		$info = null;
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultats=$connexion->query("select * FROM t_theme WHERE id = '$id'");
		$resultats->setFetchMode(PDO::FETCH_OBJ);
		while($resultat = $resultats->fetch())
		{
			$theme = new Theme();
			$theme->setId($resultat->id);
			$theme->setLibelle($resultat->libelle);
			$info = $theme;
		}
		$resultats->closeCursor();
		return $info;
	}

	public function modifier($theme)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("UPDATE t_theme SET libelle = '".$theme->getLibelle()."' WHERE id = '".$theme->getId()."'");
		return $resultat;
	}

	public function supprimer($id)
	{
		$connexion = DB::connect();
		$connexion->exec("SET CHARACTER SET utf8");
		$resultat=$connexion->exec("DELETE FROM t_theme WHERE id = '$id'");
		return $resultat;
	}
}
