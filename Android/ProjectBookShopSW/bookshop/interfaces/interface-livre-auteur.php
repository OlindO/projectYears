<?php

interface ILivreAuteur
{
	public function ajouter($livreAuteur);
	public function lister();
	public static function getById($id);
	public static function getNomPrenomAuteurByLivreId($livre_id);
	public function modifier($livreAuteur);
	public function supprimer($id);
	public static function supprimerByLivreId($livre_id);
}
