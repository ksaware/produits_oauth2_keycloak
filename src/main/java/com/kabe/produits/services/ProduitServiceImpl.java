package com.kabe.produits.services;

import com.kabe.produits.dto.ProduitDTO;
import com.kabe.produits.entites.Categorie;
import com.kabe.produits.entites.Produit;
import com.kabe.produits.exception.ResourceNotFoundException;
import com.kabe.produits.repository.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService{

    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    /*public Produit saveProduit(Produit p) {
        return produitRepository.save(p);
    }*/
    public ProduitDTO saveProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    /*public Produit updateProduit(Produit p) {
        return produitRepository.save(p);
    }*/
    public ProduitDTO updateProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }

   /* @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);
    }*/
    @Override
    public void deleteProduitById(Long id) {
        Produit p = convertDtoToEntity(getProduit(id));
        //suuprimer l'image avant de supprimer le produit
        try {
            Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        produitRepository.deleteById(id);
    }

    @Override
    /*public Produit getProduit(Long id) {
        return produitRepository.findById(id).get();
    }*/
    public ProduitDTO getProduit(Long id) {
        return convertEntityToDto(produitRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Produit","id",id)));
    }

    @Override
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll().stream().
                map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return produitRepository.findByNomPrix(nom,prix);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return produitRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return produitRepository.findByOrderByNomProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return produitRepository.trierProduitsNomPrix();
    }

    @Override
    public ProduitDTO convertEntityToDto(Produit produit) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);
        return produitDTO;
        /*ProduitDTO produitDTO = new ProduitDTO();

        return produitDTO.builder()
                .idProduit(produit.getIdProduit())
                .nomProduit(produit.getNomProduit())
                .prixProduit(produit.getPrixProduit())
                .dateCreation(produit.getDateCreation())
                .categorie(produit.getCategorie())
                .build();*/
    }

    @Override
    public Produit convertDtoToEntity(ProduitDTO produitDto) {
        Produit produit = new Produit();
        produit = modelMapper.map(produitDto, Produit.class);
        /*produit.setIdProduit(produitDto.getIdProduit());
        produit.setNomProduit(produitDto.getNomProduit());
        produit.setPrixProduit(produitDto.getPrixProduit());
        produit.setDateCreation(produitDto.getDateCreation());
        produit.setCategorie(produitDto.getCategorie());*/
        return produit;
    }
}
