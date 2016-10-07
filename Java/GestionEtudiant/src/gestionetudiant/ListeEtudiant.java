/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Heartlesss
 */
public class ListeEtudiant implements Serializable {
    // Attributs
    private ArrayList listeEtudiant = new ArrayList();
    
    //Constructeur
    public ListeEtudiant()
    {
        Etudiant temp;
        Scanner sc = new Scanner(System.in);
        int rep = 1;
        
        // Boucle de saisie des attributs du/des étudiant(s)
        while(rep == 1)
        {
            temp = new Etudiant();  // Enclenche la saisie des attributs
            listeEtudiant.add(temp);
            
            // Vérification de la condition
            System.out.print("Poursuivre la saisie ? (1 = oui, 0 = non) ");
            rep = sc.nextInt();
            System.out.println();
        }
    }
    // Méthodes
    public void ajouterEtudiant(){
        //System.out.println("Taille de la liste : " + listeEtudiant.size());
        Etudiant e = new Etudiant();
        listeEtudiant.add(e);
        System.out.println("Nouvelle taille : " + listeEtudiant.size());
    }
    
    public void afficher()
    {
        int taille = listeEtudiant.size();
        Etudiant temp;
        
        // Boucle insérant les étudiants en fin de liste
        for(int i = 0; i < taille; i++)
        {
            // Transtypage -> on force le type renvoyé à être de type Etudiant
            temp = (Etudiant)listeEtudiant.get(i);
            temp.afficheEtudiant();
        }
    }
    
    public void supprimerEtudiant()
    {
        // Saisie du nom à supprimer
        Scanner sc = new Scanner(System.in);
        System.out.print("Saisir le nom à supprimer: ");
        String nomASup = sc.nextLine();
        
        int taille = listeEtudiant.size();
        int i = 0;
        Etudiant etudiantTemp;  // 1er étudiant, pour lancer la boucle
        etudiantTemp = (Etudiant)listeEtudiant.get(i);  // Affectation de l'étudiant situé à l'indice i = 0
        
        // Boucle de recherche du nom dans la liste
        while(!nomASup.equals(etudiantTemp.getNomEtudiant()))
        {
            etudiantTemp = (Etudiant)listeEtudiant.get(i);
            i++;
        }
        
        if(i > 0)
            i--;    // Pour éviter de sortir de la liste
        
        System.out.println(">> Taille de la liste avant la suppression: " + listeEtudiant.size());
        listeEtudiant.remove(i);
        System.out.println(">> Taille de la liste après la suppression: " + listeEtudiant.size());
    }
    
    public void modifierEtudiant()
    {
        // Saisie du nom à modifier
        Scanner sc = new Scanner(System.in);
        System.out.print("Saisir le nom à modifier: ");
        String nomAModif = sc.nextLine();
        
        // Saisie des nouveaux attributs
        System.out.println("Saisir le nouvel étudiant: ");
        Etudiant nouvEtudiant = new Etudiant();
        
        // Éléments de la boucle
        int i = 0;
        Etudiant etudiantTemp;  // 1er étudiant, pour lancer la boucle
        etudiantTemp = (Etudiant)listeEtudiant.get(i);  // Affectation de l'étudiant situé à l'indice i = 0
        
        // Boucle de recherche du nom dans la liste
        while(!nomAModif.equals(etudiantTemp.getNomEtudiant()))
        {
            etudiantTemp = (Etudiant)listeEtudiant.get(i);
            i++;
        }
        
        if(i > 0)
            i--;    // Pour éviter de sortir de la liste
        
        listeEtudiant.set(i, nouvEtudiant);
    }

}