package ma.projet.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.classes.Produit;
import ma.projet.service.CommandeService;
import ma.projet.service.LigneCommandeService;
import ma.projet.service.ProduitService;
import ma.projet.classes.Categorie;
import ma.projet.service.CategorieService;

public class Test {
    public static void main(String[] args) throws ParseException {
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();
        CategorieService categorieService = new CategorieService();

        // Create categories
        Categorie categorie1 = new Categorie("C1", "Electronics", new ArrayList<>());
        Categorie categorie2 = new Categorie("C2", "Books", new ArrayList<>());
        categorieService.create(categorie1);
        categorieService.create(categorie2);

        // Create products
        Produit produit1 = new Produit("ref1", 101);
        produit1.setCategorie(categorie1);
        Produit produit2 = new Produit("ref2", 98);
        produit2.setCategorie(categorie1);
        Produit produit3 = new Produit("ref3", 123);
        produit3.setCategorie(categorie2);
        Produit produit4 = new Produit("ref4", 40);
        produit4.setCategorie(categorie2);

        // Save products
        produitService.create(produit1);
        produitService.create(produit2);
        produitService.create(produit3);
        produitService.create(produit4);

        // Test findAll
        List<Produit> allProduits = produitService.findAll();
        System.out.println("Tous les produits:");
        for (Produit p : allProduits) {
            System.out.println(p);
        }

        // Test findById
        Produit foundProduit = produitService.findById(produit1.getId());
        System.out.println("Produit trouvé par ID:");
        System.out.println(foundProduit);

        // Test update
        produit1.setPrix(150);
        produitService.update(produit1);
        System.out.println("Produit après mise à jour:");
        System.out.println(produitService.findById(produit1.getId()));

        // Test delete
        produitService.delete(produit2);
        System.out.println("Produits après suppression de produit2:");
        allProduits = produitService.findAll();
        for (Produit p : allProduits) {
            System.out.println(p);
        }

        // Test getProduitsParCategorie
        List<Produit> produitsParCategorie = produitService.getProduitsParCategorie(categorie1.getId());
        System.out.println("Produits dans la catégorie Electronics:");
        for (Produit p : produitsParCategorie) {
            System.out.println(p);
        }

        // Test getProduitsAvecPrixSuperieurA100
        List<Produit> produitsSuperieursA100 = produitService.getProduitsAvecPrixSuperieurA100();
        System.out.println("Produits avec prix supérieur à 100:");
        for (Produit p : produitsSuperieursA100) {
            System.out.println(p);
        }

        // Test LigneCommandeService
        LigneCommandeProduit ligneCommandeProduit1 = new LigneCommandeProduit();
        ligneCommandeProduit1.setProduit(produit1);        
        ligneCommandeService.create(ligneCommandeProduit1);

        // Test CommandeService
        Commande commande = new Commande();
        commande.setDate(new Date());
        commandeService.create(commande);

        // Add line items to the order
        ligneCommandeProduit1.setCommande(commande);
        ligneCommandeService.update(ligneCommandeProduit1);

        System.out.println("Commande créée avec succès.");
    }
}
