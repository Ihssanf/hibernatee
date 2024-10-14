/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;


@Embeddable
public class LigneCommandeProduitPK implements Serializable {
    private int produit;
 private int commande;
 private Date dateDebut;

    public LigneCommandeProduitPK() {
    }

    public LigneCommandeProduitPK(int produit, int commande, Date dateDebut) {
        this.produit = produit;
        this.commande = commande;
        this.dateDebut = dateDebut;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

   
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

}