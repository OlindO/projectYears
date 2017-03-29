<?php

interface IPersonne
{
	public function ajouter($personne);
	public function lister();
	public static function getById($id);
	public function getPersonneBy($email, $password);
	public function modifier($personne);
	public function supprimer($id);
}
