/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import ma.projet.entity.Produit;
import ma.projet.service.ProduitService;
import ma.projet.util.HibernateUtil;



/**
 *
 * @author MYC
 */
public class Test {
      public static void main(String[] args) throws ParseException {
    ProduitService produitService = new ProduitService();
     Scanner scanner = new Scanner(System.in);

        // Créer 5 produits
        produitService.create(new Produit("Samsung", "S23", new Date(), 1000, "Smartphone"));
        produitService.create(new Produit("Apple", "iPhone 14", new Date(), 1200, "Smartphone"));
        produitService.create(new Produit("Dell", "XPS 13", new Date(), 1500, "Laptop"));
        produitService.create(new Produit("trident", "jjjj", new Date(), 1, "bonbon"));
        produitService.create(new Produit("Lenovo", "Yoga 9i", new Date(), 1400, "Laptop")); 
       
        
        List<Produit> produits = produitService.findAll();
        for (Produit produit : produits) {
            System.out.println("ID: " + produit.getId());
            System.out.println("Marque: " + produit.getMarque());
            System.out.println("Référence: " + produit.getReference());
            System.out.println("Date d'achat: " + produit.getDateAchat());
            System.out.println("Prix: " + produit.getPrix());
            System.out.println("Désignation: " + produit.getDesignation());
            System.out.println("--------------------");
        }
        Produit produit = produitService.findById(2);

        // Afficher les informations du produit
        if (produit != null) {
            System.out.println("ID: " + produit.getId());
            System.out.println("Marque: " + produit.getMarque());
            System.out.println("Référence: " + produit.getReference());
            System.out.println("Date d'achat: " + produit.getDateAchat());
            System.out.println("Prix: " + produit.getPrix());
            System.out.println("Désignation: " + produit.getDesignation());
        } else {
            System.out.println("Produit avec l'ID 2 introuvable.");
        }
        
        System.out.println("\nSuppression du produit avec ID = 3 : ");
Produit produit3 = produitService.findById(3);
if (produit3 != null) {
    produitService.delete(produit3);
    System.out.println("Produit supprimé avec succès.");
} else {
    System.out.println("Produit avec ID 3 non trouvé.");
}


System.out.println("\nModification du produit avec ID = 1 : ");
Produit produit1 = produitService.findById(1);
if (produit1 != null) {
    // Modifier les informations du produit
    produit1.setPrix(18000); // Exemple de modification du prix
    produit1.setMarque("Hermés"); // Exemple de modification de la marque
    produit1.setDesignation("birkin"); // Exemple de modification de la désignation
    
    // Sauvegarder les modifications
    produitService.update(produit1);
    System.out.println("Produit modifié avec succès.");
} else {
    System.out.println("Produit avec ID 1 non trouvé.");
}

for (Produit p : produitService.findAll()) {
    if (p.getPrix() > 100) {
        System.out.println("ID: " + p.getId());
        System.out.println("Marque: " + p.getMarque());
        System.out.println("Référence: " + p.getReference());
        System.out.println("Date d'achat: " + p.getDateAchat());
        System.out.println("Prix: " + p.getPrix());
        System.out.println("Désignation: " + p.getDesignation());
        System.out.println("--------------------");
    }
    
     // Lire deux dates depuis le clavier
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Entrez la première date (yyyy-MM-dd) : ");
        String date1Str = scanner.nextLine();
        Date date1 = sdf.parse(date1Str);

        System.out.println("Entrez la deuxième date (yyyy-MM-dd) : ");
        String date2Str = scanner.nextLine();
        Date date2 = sdf.parse(date2Str);

        // Assurer que date1 est avant date2
        if (date1.after(date2)) {
            Date temp = date1;
            date1 = date2;
            date2 = temp;
        }

        // Afficher la liste des produits commandés entre les deux dates
        System.out.println("\nListe des produits commandés entre " + date1Str + " et " + date2Str + " :");
        List<Produit> produitsEntreDates = produitService.findAll();
        for (Produit pr : produitsEntreDates) {
            if (pr.getDateAchat().after(date1) && pr.getDateAchat().before(date2)) {
                System.out.println("ID: " + pr.getId());
                System.out.println("Marque: " + pr.getMarque());
                System.out.println("Référence: " + pr.getReference());
                System.out.println("Date d'achat: " + pr.getDateAchat());
                System.out.println("Prix: " + pr.getPrix());
                System.out.println("Désignation: " + pr.getDesignation());
                System.out.println("--------------------");
            }
        }
    }
}
}

  
