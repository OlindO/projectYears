<?php

class Auteur
{
    private $id;
    private $nom;
    private $prenom;
	
	function __construct()
	{
		$this->id = $this->getId();
		$this->nom = $this->getNom();
		$this->prenom = $this->getPrenom();
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
    public function setNom($nom)
    {
        $this->nom = $nom;
    }
    public function getNom()
    {
        return $this->nom;
    }
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }
    public function getPrenom()
    {
        return $this->prenom;
    }
}
