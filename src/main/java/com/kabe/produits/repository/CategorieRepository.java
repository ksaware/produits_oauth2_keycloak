package com.kabe.produits.repository;

import com.kabe.produits.entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "restcat")
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
