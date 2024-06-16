package com.kabe.produits.entites;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "nomProd", types = {Produit.class})
public interface ProduitProjection {
    public String getNomProduit();
    public Double getPrixProduit();
}
