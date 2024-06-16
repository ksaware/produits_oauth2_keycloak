package com.kabe.produits.services;

import com.kabe.produits.dto.CategorieDTO;
import com.kabe.produits.entites.Categorie;
import com.kabe.produits.repository.CategorieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImpl implements CategorieService{

    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategorieDTO addCategorie(CategorieDTO categorieDTO) {
        return convertCategorieEntiteToCategorieDTO(categorieRepository.save(convertCategorieDtoToCategorieEntite(categorieDTO)));
    }

    @Override
    public CategorieDTO getCategorie(Long id) {
        return convertCategorieEntiteToCategorieDTO(categorieRepository.getById(id));
    }

    @Override
    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll().stream()
                .map(this::convertCategorieEntiteToCategorieDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieDTO updateCategorie(CategorieDTO categorieDTO) {
        return convertCategorieEntiteToCategorieDTO(categorieRepository.save(convertCategorieDtoToCategorieEntite(categorieDTO)));
    }

    @Override
    public void deleteCategorie(Long id) {
         categorieRepository.deleteById(id);
    }

    @Override
    public CategorieDTO convertCategorieEntiteToCategorieDTO(Categorie categorie) {
        return modelMapper.map(categorie,CategorieDTO.class);
    }

    @Override
    public Categorie convertCategorieDtoToCategorieEntite(CategorieDTO categorieDTO) {
        return modelMapper.map(categorieDTO, Categorie.class);
    }
}
