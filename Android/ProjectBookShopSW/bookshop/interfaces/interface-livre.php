<?php

interface ILivre
{
	public function ajouter($livre);
	public function lister();
	public static function getById($id);
	public function modifier($livre);
	public function supprimer($id);
}
