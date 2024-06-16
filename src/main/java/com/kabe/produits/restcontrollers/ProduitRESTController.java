package com.kabe.produits.restcontrollers;

import com.kabe.produits.dto.ProduitDTO;
import com.kabe.produits.entites.Produit;
import com.kabe.produits.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProduitRESTController {

    @Autowired
    ProduitService produitService;
    @RequestMapping(path ="all", method = RequestMethod.GET)
   /* public List<Produit> getAllProduits(){
        return produitService.getAllProduits();
    }*/
    public List<ProduitDTO> getAllProduits(){
        return produitService.getAllProduits();
    }

    @RequestMapping(value="/getbyid/{id}",method = RequestMethod.GET)
    /*public Produit getProduitById(@PathVariable("id") Long id){
        return produitService.getProduit(id);
    }*/
    public ProduitDTO getProduitById(@PathVariable("id") Long id){
        return produitService.getProduit(id);
    }

    @RequestMapping(value = "/addprod",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')") //Pour restreindre l'accès à cet api avec @EnableMethodSecurity dans SecurityConfig
    /*public Produit createProduit(@RequestBody Produit produit){
        return produitService.saveProduit(produit);
    }*/
    public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO){
        return produitService.saveProduit(produitDTO);
    }

    @RequestMapping(value ="/updateprod", method = RequestMethod.PUT)
    /*public Produit updateProduit(@RequestBody Produit produit){
        return produitService.updateProduit(produit);
    }*/
    public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO){
        return produitService.updateProduit(produitDTO);
    }

    @RequestMapping(value = "/delprod/{id}", method = RequestMethod.DELETE)
    public void deleteProduit(@PathVariable("id") Long id){
        produitService.deleteProduitById(id);
    }

    @RequestMapping(value = "/prodscat/{idcat}", method = RequestMethod.GET)
    public List<Produit> getProduitByCatId(@PathVariable("idcat") Long idcat){
        return produitService.findByCategorieIdCat(idcat);
    }

    @RequestMapping(value = "/prodsByName/{nom}", method = RequestMethod.GET)
    public List<Produit> findByNomProduitContains(@PathVariable("nom") String nom){
        return produitService.findByNomProduitContains(nom);
    }

    @GetMapping("/auth")
    Authentication getAuth(Authentication auth)
    {
        return auth;
    }
}
