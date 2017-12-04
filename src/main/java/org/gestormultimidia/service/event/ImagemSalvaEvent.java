package org.gestormultimidia.service.event;

import org.gestormultimidia.model.Imagem;
import org.springframework.util.StringUtils;

public class ImagemSalvaEvent {
	
	private Imagem imagem;

	public ImagemSalvaEvent(Imagem imagem) {
		this.imagem = imagem;
	}

	public final Imagem getImagem() {
		return imagem;
	}
	
	public boolean temFoto(){
		return !StringUtils.isEmpty(imagem.getNomeFoto());
	}
}