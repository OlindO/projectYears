<?php

class LivreTheme
{
	private $id;
	private $livre;
	private $theme;

	public function __construct(){
		$this->id = $this->getId();
		$this->livre = $this->getLivre();
		$this->theme = $this->getTheme();
	}

	// Getter & Setter
	public function setId($id)
	{
		$this->id = $id;
	}
	public function getId()
	{
		return $this->id;
	}
	public function setLivre($livre)
	{
		$this->livre = $livre;
	}
	public function getLivre()
	{
		return $this->livre;
	}
	public function setTheme($theme)
	{
		$this->theme = $theme;
	}
	public function getTheme()
	{
		return $this->theme;
	}
}

