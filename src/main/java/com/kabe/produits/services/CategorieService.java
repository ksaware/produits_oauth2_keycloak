package com.kabe.produits.services;

import com.kabe.produits.dto.CategorieDTO;
import com.kabe.produits.entites.Categorie;

import java.util.List;

public interface CategorieService {

    CategorieDTO addCategorie(CategorieDTO categorieDTO);
    CategorieDTO getCategorie(Long id);
     List<CategorieDTO> getAllCategories();
    CategorieDTO updateCategorie(CategorieDTO categorieDTO);
     void deleteCategorie(Long id);
     CategorieDTO convertCategorieEntiteToCategorieDTO(Categorie categorie);
    Categorie convertCategorieDtoToCategorieEntite(CategorieDTO categorieDTO);
}
