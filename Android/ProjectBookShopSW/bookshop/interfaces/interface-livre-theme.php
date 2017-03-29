<?php

interface ILivreTheme
{
	public function ajouter($livreTheme);
	public function lister();
	public static function getById($id);
	public static function getLibelleThemeByLivreId($livre_id);
	public function modifier($livreTheme);
	public function supprimer($id);
	public static function supprimerByLivreId($livre_id);
}
