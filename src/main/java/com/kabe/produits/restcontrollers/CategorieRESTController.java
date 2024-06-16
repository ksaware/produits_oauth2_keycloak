package com.kabe.produits.restcontrollers;

import com.kabe.produits.dto.CategorieDTO;
import com.kabe.produits.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategorieRESTController {

    @Autowired
    CategorieService categorieService;

    @RequestMapping(method = RequestMethod.POST)
    public CategorieDTO addCategorie(@RequestBody CategorieDTO categorieDTO){
        return categorieService.addCategorie(categorieDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategorieDTO> getAllCategories(){

        return categorieService.getAllCategories();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CategorieDTO getCategorieById(@PathVariable("id") Long id){
        return categorieService.getCategorie(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategorieById(@PathVariable("id") Long id){
         categorieService.deleteCategorie(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CategorieDTO updateCategorie(@RequestBody CategorieDTO categorieDTO){
        return categorieService.updateCategorie(categorieDTO);
    }
}
