package com.kabe.produits.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kabe.produits.entites.Produit;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorieDTO {

    private Long idCat;
    private String nomCat;
    private String descCat;
    List<Produit> produits;
}
