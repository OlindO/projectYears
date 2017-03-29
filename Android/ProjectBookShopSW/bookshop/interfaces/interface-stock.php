<?php

interface IStock
{
	public function ajouter($stock);
	public function lister();
	public static function getById($id);
	public static function getQuantiteByLivreId($id);
	public function modifier($stock);
	public function supprimer($id);
}
