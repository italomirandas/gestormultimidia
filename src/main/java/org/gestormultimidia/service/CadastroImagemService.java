package org.gestormultimidia.service;

import org.gestormultimidia.dao.ImagemDAO;
import org.gestormultimidia.model.Imagem;
import org.gestormultimidia.service.event.ImagemSalvaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CadastroImagemService {

	@Autowired
	private ImagemDAO produtos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar (Imagem imagem){
		produtos.gravar(imagem);
		
		publisher.publishEvent(new ImagemSalvaEvent(imagem));
		
	}
}