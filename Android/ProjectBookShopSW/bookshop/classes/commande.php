<?php

class Commande
{
    private $id;
    private $numero;
    private $montant;
    private $personne;
    private $date;
	
	function __construct()
	{
		$this->id = $id;
		$this->numero = $numero;
		$this->montant = $montant;
		$this->personne = $personne;
		$this->date = $date;
	}

	// Getter & Setter
    public function getId()
    {
        return $this->id;
    }
    public function setNumero($numero)
    {
        $this->numero = $numero;
    }
    public function getNumero()
    {
        return $this->numero;
    }
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }
    public function getQuantite()
    {
        return $this->quantite;
    }
    public function setMontant($montant)
    {
        $this->montant = $montant;
    }
    public function getMontant()
    {
        return $this->montant;
    }
    public function setPersonne($personne)
    {
        $this->personne = $personne;
    }
    public function getPersonne()
    {
        return $this->personne;
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
