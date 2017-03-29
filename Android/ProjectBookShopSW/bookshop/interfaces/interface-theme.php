<?php


interface ITheme
{
	public function ajouter($theme);
	public function lister();
	public static function getById($id);
	public function modifier($theme);
	public function supprimer($id);
}
