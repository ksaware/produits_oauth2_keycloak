package com.kabe.produits.services;

import com.kabe.produits.dto.ProduitDTO;
import com.kabe.produits.entites.Categorie;
import com.kabe.produits.entites.Produit;

import java.util.List;

public interface ProduitService {
    //Produit saveProduit(Produit p);
    //ProduitDTO saveProduit(Produit p);
    ProduitDTO saveProduit(ProduitDTO p);
    ProduitDTO updateProduit(ProduitDTO p);
    void deleteProduit(Produit p);
    void deleteProduitById(Long id);
   // Produit getProduit(Long id);
    ProduitDTO getProduit(Long id);
    //List<Produit> getAllProduits();
    List<ProduitDTO> getAllProduits();
    List<Produit> findByNomProduit(String nom);
    List<Produit> findByNomProduitContains(String nom);
    List<Produit> findByNomPrix (String nom, Double prix);
    List<Produit> findByCategorie (Categorie categorie);
    List<Produit> findByCategorieIdCat(Long id);
    List<Produit> findByOrderByNomProduitAsc();
    List<Produit> trierProduitsNomsPrix();
    ProduitDTO convertEntityToDto(Produit produit);
    Produit convertDtoToEntity(ProduitDTO produitDto);

}
