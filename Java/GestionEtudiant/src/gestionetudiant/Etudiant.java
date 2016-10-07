/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Heartlesss
 */
public class Etudiant implements Serializable {
    // Attributs
    private String nomEtudiant;
    private String prenomEtudiant;
    
    // Constructeur 
    public Etudiant()
    {
        Scanner sc = new Scanner(System.in);
        // Saisie du nom et du prénom
        System.out.print("Nom: ");
        nomEtudiant = sc.nextLine();
        System.out.print("Prénom: ");
        prenomEtudiant = sc.nextLine();
    }
    // methode 
    public String getNomEtudiant(){
        return nomEtudiant;
    }
    
    public String getPrenomEtudiant(){
        return prenomEtudiant;
    }
    
    public void setNomEtudiant(String nom){
        nomEtudiant = nom;
    }
    
    public void setPrenomEtudiant(String prenom){
        prenomEtudiant = prenom;
    }
    
    public void afficheEtudiant(){
        System.out.println(nomEtudiant + " " + prenomEtudiant);
    }
}
