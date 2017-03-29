<?php

class Stock
{
    private $id;
    private $quantite;	
    private $livre;
    private $date;

	public function __construct()
	{
		$this->id = $this->getId();
		$this->quantite = $this->getQuantite();
		$this->livre = $this->getLivre();
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

    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }

    public function getQuantite()
    {
        return $this->quantite;
    }
	
    public function setLivre($livre)
    {
        $this->livre = $livre;
    }

    public function getLivre()
    {
        return $this->livre;
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
