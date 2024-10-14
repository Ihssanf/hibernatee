/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


@Entity
public class LigneCommandeProduit implements Serializable {

    @EmbeddedId
    private LigneCommandeProduitPK pk;
    @JoinColumn(name = "commande",insertable = false,updatable = false)
    @ManyToOne
    private Commande commande;
    
    
    @JoinColumn(name = "produit",insertable = false,updatable = false)
    @ManyToOne
    private Produit produit;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;

    public LigneCommandeProduit() {
    }

    public LigneCommandeProduit(Commande commande, Produit produit, Date dateFin) {
        this.commande = commande;
        this.produit = produit;
        this.dateFin = dateFin;
    }

    public LigneCommandeProduitPK getPk() {
        return pk;
    }

    public void setPk(LigneCommandeProduitPK pk) {
        this.pk = pk;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


   
}