<?php

class LivreCommande
{
    private $id;
    private $livre;
    private $commande;
    private $quantite;

	public function __construct(){
        $this->id = $id;
        $this->livre = $livre;
        $this->commande = $commande;
        $this->quantite = $quantite;
	}
	
	// Getter & Setter
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
    public function setCommande($commande)
    {
        $this->commande = $commande;
    }
    public function getCommande()
    {
        return $this->commande;
    }
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }
    public function getQuantite()
    {
        return $this->quantite;
    }
}
