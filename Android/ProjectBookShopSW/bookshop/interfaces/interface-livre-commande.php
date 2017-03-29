<?php

interface ILivreCommande
{
	public function ajouter($livreCommande);
	public function lister();
	public static function getById($id);
	public function modifier($livreCommande);
	public function supprimer($id);
}
