<?php


class Theme
{
    private $id;
    private $libelle;

	public function __construct()
	{
		$this->id = $this->getId();
		$this->libelle = $this->getLibelle();
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
    public function setLibelle($libelle)
    {
        $this->libelle = $libelle;
    }
    public function getLibelle()
    {
        return $this->libelle;
    }
}
