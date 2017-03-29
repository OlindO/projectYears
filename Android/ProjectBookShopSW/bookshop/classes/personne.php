<?php

class Personne
{
    private $id;
    private $nom;
    private $prenom;
    private $email;
    private $password;
    private $civilite;
    private $photo;
    private $date;

	
	public function __construct(){
		$this->id = $this->getId();
        $this->nom = $this->getNom();
		$this->prenom = $this->getPrenom();
		$this->email = $this->getEmail();
		$this->password = $this->getPassword();
		$this->civilite = $this->getCivilite();
		$this->photo = $this->getPhoto();
		$this->date = $this->getDate();
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
    public function setEmail($email)
    {
        $this->email = $email;
    }
    public function getEmail()
    {
        return $this->email;
    }
    public function setPassword($password)
    {
        $this->password = $password;
    }
    public function getPassword()
    {
        return $this->password;
    }
    public function setCivilite($civilite)
    {
        $this->civilite = $civilite;
    }
    public function getCivilite()
    {
        return $this->civilite;
    }
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }
    public function getPhoto()
    {
        return $this->photo;
    }
    public function setDate($date)
    {
    	$this->date = $date;
    }
    public function getDate()
    {
    	return $this->date;
    }
}
