package org.gestormultimidia.controllers;

import org.gestormultimidia.dto.FotoDTO;
import org.gestormultimidia.storage.FotoStorage;
import org.gestormultimidia.storage.FotoStorageRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/fotos")
public class FotosController {
	
	@Autowired
	private FotoStorage fotoStorage;

	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files){
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado, fotoStorage));
		thread.start();
		
		return resultado;
	}
	
	@GetMapping("/temp/{nomeFoto:.*}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nomeFoto){
		return fotoStorage.recuperarFotoTemporaria(nomeFoto);
	}
	
	@GetMapping("/{nomeFoto:.*}")
	public byte[] recuperar(@PathVariable String nomeFoto){
		System.out.println("");
		System.out.println("Dentro de FotosController.recuperar(nomeFoto): " + nomeFoto);
		return fotoStorage.recuperar(nomeFoto);
	}
}

