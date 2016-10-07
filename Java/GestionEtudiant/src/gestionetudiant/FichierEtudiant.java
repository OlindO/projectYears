
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Heartlesss
 */
public class FichierEtudiant {
    // Attributs
    private ObjectOutputStream ofW;
    private ObjectInputStream ofR;
    private String nomFichier = "fEtudiant.dat";
    private char mode;
    
    // Méthodes
    
    // Permet de vérifier si un fichier est ouvert
    public boolean ouvrir(String ch)
    {
        // La méthode retourne vrai si le fichier est ouvert en lecture (if) ou en écriture (else)
	try{
            mode = (ch.toUpperCase().charAt(0));    // Récupère la première lettre de la chaîne de caractères.
            if(mode == 'R' || mode == 'L'){
                ofR = new ObjectInputStream(new FileInputStream(nomFichier));
                System.out.println(">> Fichier ouvert en lecture");}
            else if(mode == 'E' || mode == 'W'){
                ofW = new ObjectOutputStream(new FileOutputStream(nomFichier));
                System.out.println(">> Fichier ouvert en écriture");}
            return true;
	}
	catch(IOException e){
            return false;
	}
    }
    
    // Lit un fichier d'étudiants et le renvoie sous forme d'une liste
    public ListeEtudiant lire() throws IOException, ClassNotFoundException
    {
            ListeEtudiant tmp = (ListeEtudiant)ofR.readObject();
            return tmp;        
    }
    
    // Écrit une liste d'étudiants sur un fichier
    public void ecrire(ListeEtudiant tmp) throws IOException
    {
        if(tmp != null)
            ofW.writeObject(tmp);
    }
    
    // Ferme le fichier
    public void fermer() throws IOException
    {
        if(mode == 'R' || mode == 'L'){
            System.out.println(">> Fichier fermé en lecture");
            ofR.close();}
        else if(mode == 'E' || mode == 'W'){
            System.out.println(">> Fichier fermé en écriture");
            ofW.close();}
    }
            
}
