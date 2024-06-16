package com.kabe.produits.repository;

import com.kabe.produits.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "image")
public interface ImageRepository extends JpaRepository<Image, Long> {
}
