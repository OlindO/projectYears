<?php

interface ICommande
{
	public function ajouter($commande);
	public function lister();
	public static function getById($id);
	public function modifier($commande);
	public function supprimer($id);
}
