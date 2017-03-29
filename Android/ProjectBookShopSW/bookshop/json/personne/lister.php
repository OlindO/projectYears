<?php
$resultat = array();
$daoPersonne = new DAOPersonne();
$personnes = $daoPersonne->lister();
//echo "<pre>"; print_r($resultat); echo "</pre>";

$compteur = 0;
foreach ($personnes as $p){
	$resultat[] = array(
			"id" => $p->getId(), 
			"civilite" => $p->getCivilite(), 
			"nom" => $p->getNom(), 
			"prenom" => $p->getPrenom(), 
			"email" => $p->getEmail(), 
			"password" => $p->getPassword(), 
			"photo" => $p->getPhoto());
}
//--
echo json_encode($resultat);
?>