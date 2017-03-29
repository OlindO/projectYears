<?php

interface IAuteur
{
	public function ajouter($auteur);
	public function lister();
	public static function getById($id);
	public function modifier($auteur);
	public function supprimer($id);
}
