/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;



import static java.nio.file.Files.list;
import static java.util.Collections.list;
import java.util.List;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.dao.IDao;

import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class LigneCommandeService implements IDao<LigneCommandeProduit> {

    @Override
    public boolean create(LigneCommandeProduit o) {
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
    public boolean update(LigneCommandeProduit o) {
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
    public boolean delete(LigneCommandeProduit o) {
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
    public LigneCommandeProduit findById(int id) {
        Session session = null;
        Transaction tx = null;
        boolean etat= false;
        LigneCommandeProduit ligneCommandeligneCommandeligneCommandeproduit = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       ligneCommandeligneCommandeligneCommandeproduit= (LigneCommandeProduit) session.get(LigneCommandeProduit.class, id);
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return ligneCommandeligneCommandeligneCommandeproduit;
        }    
    }

    @Override
    public List<LigneCommandeProduit> findAll() {
    Session session = null;
        Transaction tx = null;
        boolean etat= false;
        List <LigneCommandeProduit> ligneCommandeligneCommandeligneCommandeproduits = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       ligneCommandeligneCommandeligneCommandeproduits = session.createQuery("from LigneCommandeProduit").list();
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return ligneCommandeligneCommandeligneCommandeproduits;
        }       }

}