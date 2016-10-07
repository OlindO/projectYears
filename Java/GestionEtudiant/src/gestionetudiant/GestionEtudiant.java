/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Heartlesss
 */
public class GestionEtudiant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        exoListe();
        //exoDico();
        //exoFichier();
        exoFichier2();
    }
        
    public static void exoListe()
    {
        // On affiche notre liste 
        System.out.println("Création de la liste: ");
        ListeEtudiant maListe = new ListeEtudiant();
        System.out.println();   // SDL
        System.out.println("Liste: ");
        maListe.afficher();
        System.out.println();   // SDL
        
        // Affichage du menu
        System.out.println("1 - Ajouter un étudiant");
        System.out.println("2 - Modifier un étudiant");
        System.out.println("3 - Supprimer un étudiant");
        System.out.println("4 - Sortir");
        
        // Saisie de la condition principale du menu
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println();   // SDL
        
        if(i != 1 && i != 2 && i != 3 && i != 4)
        {
            System.out.println("Veuillez entrer une valeur correcte: ");
            
            // Boucle tant que i n'est pas compris entre 1 et 4
            while(i != 1 && i != 2 && i != 3 && i != 4)
            {
                System.out.println("1 - Ajouter un étudiant");
                System.out.println("2 - Modifier un étudiant");
                System.out.println("3 - Supprimer un étudiant");
                System.out.println("4 - Sortir");
                i = sc.nextInt();
                System.out.println();   // SDL
            }
        }
        
        switch(i)
        {
            case 1 :
                maListe.ajouterEtudiant();
                System.out.println();   // SDL
                break;
            case 2 :
                maListe.modifierEtudiant();
                System.out.println();   // SDL
                break;
            case 3 :
                maListe.supprimerEtudiant();
                System.out.println();   // SDL
                break;
        }
        
        maListe.afficher();
    }
    
    public static void exoDico()
    {
        DicoEtudiant monDico = new DicoEtudiant();
        
    }
    
    public static void exoFichier() throws IOException, ClassNotFoundException
    {
        // Création des objets
        ListeEtudiant maListe = new ListeEtudiant();
        FichierEtudiant F = new FichierEtudiant();
        
        // Écriture de la liste sur le fichier
        if(F.ouvrir("Ecriture"))
        {
            System.out.println(">> Écriture de la liste sur le fichier");
            F.ecrire(maListe);
            F.fermer();
        }
        
        // Lecture du fichier, affectaton à une liste puis affichage de cette liste
        if(F.ouvrir("Lecture"))
        {
            System.out.println(">> Lecture du fichier, affectaton à une liste puis affichage de cette liste");
            maListe = F.lire();
            maListe.afficher();
            System.out.println(">> Fin du fichier");
            F.fermer();
        }
    }
    
    public static void exoFichier2() throws IOException, ClassNotFoundException
    {
        ListeEtudiant maListe = new ListeEtudiant();
        FichierEtudiant F = new FichierEtudiant();
        
        // Ouverture du fichier en écriture
        if(F.ouvrir("Ecriture"))
        {
            System.out.println(">> Écriture de la liste sur le fichier");
            F.ecrire(maListe);
            F.fermer();
        }
        
        // Ouverture du fichier en lecture
        if(F.ouvrir("Lecture"))
        {
            System.out.println(">> Affichage de la liste: ");
            maListe.afficher();
            System.out.println(">> Affichage du fichier: ");
            ListeEtudiant listeLue = F.lire();
            listeLue.afficher();
            
            Scanner sc = new Scanner(System.in);
            int i = 0;
            System.out.println();   // SDL

            // Boucle tant que i est compris entre 1 et 3
            do {
                // Affichage du menu
                System.out.println("1 - Ajouter un étudiant");
                System.out.println("2 - Modifier un étudiant");
                System.out.println("3 - Supprimer un étudiant");
                System.out.println("4 - Sortir");
                
                // Saisie de la condition i du menu
                i = sc.nextInt();
                System.out.println();   // SDL
                
                // Boucle tant que i n'est pas compris entre 1 et 4
                while(i < 1 ||i > 4)
                {
                    System.out.println("Veuillez entrer une valeur correcte: ");
                    i = sc.nextInt();
                    System.out.println();   // SDL
                }
                
                // switch correspondant aux options du menu
                switch(i)
                {
                    case 1 :
                        maListe.ajouterEtudiant();
                        System.out.println();   // SDL
                        break;
                    case 2 :
                        maListe.modifierEtudiant();
                        System.out.println();   // SDL
                        break;
                    case 3 :
                        maListe.supprimerEtudiant();
                        System.out.println();   // SDL
                        break;
                }
            }while(0 < i && i < 4);
            F.fermer();
        }
        
        // Ouverture du fichier en écriture
        boolean ouv = F.ouvrir("Ecriture");
        if(ouv)
        {
            // Écriture de la liste sur le fichier
            F.ecrire(maListe);
            //F.afficher();
            F.fermer();
        }
    }

}
