/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;



import static java.nio.file.Files.list;
import static java.util.Collections.list;
import java.util.List;
import ma.projet.classes.Commande;
import ma.projet.dao.IDao;

import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class CommandeService implements IDao<Commande> {

    @Override
    public boolean create(Commande o) {
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
    public boolean update(Commande o) {
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
    public boolean delete(Commande o) {
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
    public Commande findById(int id) {
        Session session = null;
        Transaction tx = null;
        boolean etat= false;
        Commande commande = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       commande= (Commande) session.get(Commande.class, id);
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return commande;
        }    
    }

    @Override
    public List<Commande> findAll() {
    Session session = null;
        Transaction tx = null;
        boolean etat= false;
        List <Commande> commandes = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       commandes = session.createQuery("from Commande").list();
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return commandes;
        }       }

    
    
}