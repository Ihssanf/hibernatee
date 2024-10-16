/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;


import dao.IDao;

import java.util.List;
import ma.projet.entity.Produit;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author MYC
 */
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

}