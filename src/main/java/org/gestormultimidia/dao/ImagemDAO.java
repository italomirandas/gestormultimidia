package org.gestormultimidia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.gestormultimidia.model.Imagem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImagemDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Imagem imagem){
		manager.persist(imagem);
		
	}

	public List<Imagem> listar() {
		
		return manager.createQuery("select p from Imagem p", Imagem.class).getResultList();
	}
	
}
