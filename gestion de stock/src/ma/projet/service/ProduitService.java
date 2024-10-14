/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import static java.nio.file.Files.list;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;

import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Scanner;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;



public class ProduitService implements IDao<Produit> {

    @Override
    public boolean create(Produit o) {
        Session session = null;
        Transaction tx = null;
        boolean etat= false;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(o);
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return etat;
        }
        
    }

    @Override
    public boolean update(Produit o) {
 Session session = null;
        Transaction tx = null;
        boolean etat= false;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.update(o);
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return etat;
        }    }

    @Override
    public boolean delete(Produit o) {
 Session session = null;
        Transaction tx = null;
        boolean etat= false;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.delete (o);
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return etat;
        }
    }

    @Override
    public Produit findById(int id) {
        Session session = null;
        Transaction tx = null;
        boolean etat= false;
        Produit produit = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       produit= (Produit) session.get(Produit.class, id);
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return produit;
        }    
    }

    @Override
    public List<Produit> findAll() {
    Session session = null;
        Transaction tx = null;
        boolean etat= false;
        List <Produit> produits = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       produits = session.createQuery("from Produit").list();
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return produits;
        }       }
    
public List<Produit> getProduitsParCategorie(int categorieId) {
    List<Produit> produits = null;
    Session session = null;
    Transaction tx = null;

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        // HQL query to get produits associated with the given categorie
        String hql = "SELECT p FROM Produit p " +
                     "JOIN p.categorie AS c " +
                     "WHERE c.id = :categorieId";

        produits = session.createQuery(hql)
                          .setParameter("categorieId", categorieId)
                          .list();

        tx.commit();
    } catch (HibernateException ex) {
        if (tx != null) {
            tx.rollback();
        }
        ex.printStackTrace();  // Log the exception
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return produits;
}

    public List<Produit> getProduitsCommandesEntreDeuxDates(Date dateDebut, Date dateFin) {
        List<Produit> produitsCommandes = null;
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Requête HQL pour sélectionner les produits commandés entre les deux dates
            String hql = "SELECT p FROM Produit p " +
                         "JOIN p.commandes AS c " +
                         "WHERE c.dateCommande BETWEEN :dateDebut AND :dateFin";

            produitsCommandes = session.createQuery(hql)
                    .setParameter("dateDebut", dateDebut)
                    .setParameter("dateFin", dateFin)
                    .list();

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return produitsCommandes;
    }

    // Méthode pour demander à l'utilisateur de saisir les dates et afficher les résultats
    public void afficherProduitsCommandesParDates() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Entrez la date de début (format: yyyy-MM-dd): ");
        String dateDebutStr = scanner.nextLine();
        Date dateDebut = sdf.parse(dateDebutStr);
        System.out.print("Entrez la date de fin (format: yyyy-MM-dd): ");
        String dateFinStr = scanner.nextLine();
        Date dateFin = sdf.parse(dateFinStr);
        List<Produit> produits = getProduitsCommandesEntreDeuxDates(dateDebut, dateFin);
        if (produits != null && !produits.isEmpty()) {
            System.out.println("Produits commandés entre " + dateDebutStr + " et " + dateFinStr + " :");
            for (Produit p : produits) {
                System.out.println(p);
            }
        } else {
            System.out.println("Aucun produit n'a été commandé entre ces dates.");
        }
    }
    
    
  public List<Produit> getProduitsCommande(Commande commande) {
    Session session = null;
    Transaction tx = null;
    List<Produit> produits = new ArrayList<>();
    
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        
        // Utiliser une requête HQL pour récupérer les produits à partir de la commande
        String hql = "SELECT lcp.produit FROM LigneCommandeProduit lcp " +
                     "WHERE lcp.commande = :commande";
        
        Query query = session.createQuery(hql);
        query.setParameter("commande", commande);
        
        produits = query.list();
        
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
    
    return produits;
}

public List<Produit> getProduitsAvecPrixSuperieurA100() {
    Session session = null;
    Transaction tx = null;
    List<Produit> produits = null;

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        // Requête HQL pour sélectionner les produits dont le prix est supérieur à 100 DH
        String hql = "FROM Produit p WHERE p.prix > 100";
        Query query = session.createQuery(hql);
        
        produits = query.list();
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return produits;
}

}