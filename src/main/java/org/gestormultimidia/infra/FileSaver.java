package org.gestormultimidia.infra;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest request;
	
	public String write (String baseFolder, MultipartFile file){
		try {
			
			//String realPath = request.getServletContext().getRealPath("/"+baseFolder);
			String realPath = getDefault().getPath(System.getenv("HOME"), ".produtofotos").toString();
			String novoNome = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			String path = realPath + "/" + baseFolder + "/" + novoNome;
			file.transferTo(new File(path));
		
			return baseFolder + "/" + file.getOriginalFilename();  // caminho relativo
			//return path;   -> assim salva o caminho absoluto
			
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
