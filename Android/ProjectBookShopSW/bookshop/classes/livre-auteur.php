<?php

class LivreAuteur
{
    private $id;
    private $livre;
    private $auteur;

	public function __construct(){
        $this->id = $this->getId();
        $this->livre = $this->getLivre();
        $this->auteur = $this->getAuteur();
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
    public function setAuteur($auteur)
    {
        $this->auteur = $auteur;
    }
    public function getAuteur()
    {
        return $this->auteur;
    }
}
