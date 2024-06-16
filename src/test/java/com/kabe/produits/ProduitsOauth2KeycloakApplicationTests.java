package com.kabe.produits;

import com.kabe.produits.entites.Categorie;
import com.kabe.produits.entites.Produit;
import com.kabe.produits.repository.CategorieRepository;
import com.kabe.produits.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ProduitsOauth2KeycloakApplicationTests {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	CategorieRepository categorieRepository;

	/*@Test
	void testAjoutProduit(){
		Categorie c = new Categorie();
		c.setIdCat(3L);
		Produit p = new Produit("PS 5",200.00,new Date(),c);
		produitRepository.save(p);
	}*/

	@Test
	void testFindProduit(){
		Produit p = produitRepository.findById(1L).get();
		System.out.println(p);
	}
	@Test
	void testFindByNomProduit(){
		List<Produit> produits = produitRepository.findByNomProduit("PS 4");
		for(Produit p : produits)
			System.out.println(p);
	}

	@Test
	void testFindByNomProduitContains(){
		List<Produit> produits = produitRepository.findByNomProduitContains("PS");
		for(Produit p : produits)
			System.out.println(p);
	}

	@Test
	void testUpdateProduit(){
		Produit p = produitRepository.findById(1L).get();
		p.setPrixProduit(250.00);
		produitRepository.save(p);
		System.out.println(p);
	}

	@Test
	void testDeleteProduit(){
		produitRepository.deleteById(1L);
	}

	@Test
	void testFindAllProduits(){
		List<Produit> produits = produitRepository.findAll();
		for(Produit p : produits)
		   System.out.println(p);
	}

	@Test
	void testAjoutCategorie(){
		Categorie c = new Categorie();
		c.setNomCat("Cat 4");
		c.setDescCat("Categorie 4");
		categorieRepository.save(c);
	}

	@Test
	void testFindByNomPrix(){
		List<Produit> produits = produitRepository.findByNomPrix("PS 5",300.0);
		for(Produit p : produits)
			System.out.println(p);
	}

	@Test
	void testFindByCategorie(){
		Categorie c = new Categorie();
		c.setIdCat(1L);
		List<Produit> produits = produitRepository.findByCategorie(c);
		for(Produit p : produits)
			System.out.println(p);
	}

	@Test
	void testFindByCategorieIdCat(){
		List<Produit> produits = produitRepository.findByCategorieIdCat(2L);
		for(Produit p : produits)
			System.out.println(p);
	}

	@Test
	void testFindByOrderByNomProduitAsc(){
		List<Produit> produits = produitRepository.findByOrderByNomProduitAsc();
		for(Produit p : produits)
			System.out.println(p);
	}

	@Test
	void testrierProduitsNomPrix(){
		List<Produit> produits = produitRepository.trierProduitsNomPrix();
		for(Produit p : produits)
			System.out.println(p);
	}
}
