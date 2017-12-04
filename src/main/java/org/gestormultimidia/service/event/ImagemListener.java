package org.gestormultimidia.service.event;

import org.gestormultimidia.storage.FotoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ImagemListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto()")
	public void imagemSalva (ImagemSalvaEvent evento){
		
		System.out.println("\n>>>> tem foto sim: " + evento.getImagem().getNomeFoto());
		
		fotoStorage.salvar(evento.getImagem().getNomeFoto());
	}

}
