<?php

class Livre
{
    private $id;
    private $titre;
    private $description;
    private $prix;
    private $isbn;
    private $photo;

	public function __construct(){
        $this->id = $this->getId();
        $this->titre = $this->getTitre();
        $this->description = $this->getDescription();
        $this->prix = $this->getPrix();
        $this->isbn = $this->getIsbn();
        $this->photo = $this->getPhoto();
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
    public function setTitre($titre)
    {
        $this->titre = $titre;
    }
    public function getTitre()
    {
        return $this->titre;
    }
    public function setDescription($description)
    {
        $this->description = $description;
    }
    public function getDescription()
    {
        return $this->description;
    }
    public function setPrix($prix)
    {
        $this->prix = $prix;
    }
    public function getPrix()
    {
        return $this->prix;
    }
    public function setIsbn($isbn)
    {
        $this->isbn = $isbn;
    }
    public function getIsbn()
    {
        return $this->isbn;
    }
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }
    public function getPhoto()
    {
        return $this->photo;
    }
}
