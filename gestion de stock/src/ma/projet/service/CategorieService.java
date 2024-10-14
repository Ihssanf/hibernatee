/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;


    



import static java.nio.file.Files.list;
import static java.util.Collections.list;
import java.util.List;
import ma.projet.classes.Categorie;
import ma.projet.dao.IDao;

import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author MYC
 */
public class CategorieService implements IDao<Categorie> {

    @Override
    public boolean create(Categorie o) {
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
    public boolean update(Categorie o) {
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
    public boolean delete(Categorie o) {
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
    public Categorie findById(int id) {
        Session session = null;
        Transaction tx = null;
        boolean etat= false;
        Categorie categorie = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       categorie= (Categorie) session.get(Categorie.class, id);
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return categorie;
        }    
    }

    @Override
    public List<Categorie> findAll() {
    Session session = null;
        Transaction tx = null;
        boolean etat= false;
        List <Categorie> categories = null ;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
       categories = session.createQuery("from Categorie").list();
        tx.commit();
        etat= true;
    }catch(HibernateException e){
        if (tx!=null)
            tx.rollback();
    }finally{
            if (session!=null)
                session.close();
            return categories;
        }       }

}
