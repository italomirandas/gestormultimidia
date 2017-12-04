package org.gestormultimidia.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	public String salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nomeFoto);

	public void salvar(String nomeFoto);

	public byte[] recuperar(String nomeFoto);

}
