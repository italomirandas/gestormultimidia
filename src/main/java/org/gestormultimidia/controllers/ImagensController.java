package org.gestormultimidia.controllers;

import java.util.List;

import javax.validation.Valid;

import org.gestormultimidia.dao.ImagemDAO;
import org.gestormultimidia.infra.FileSaver;
import org.gestormultimidia.model.Imagem;
import org.gestormultimidia.service.CadastroImagemService;
import org.gestormultimidia.validation.ImagemValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/imagens")
public class ImagensController {

	@Autowired
	private CadastroImagemService imagens;
	
	@Autowired
	private ImagemDAO imagemDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ImagemValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("imagens/form");
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)   
	public ModelAndView gravar(MultipartFile sumario, @Valid Imagem imagem, BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println(imagem);
		System.out.println(sumario.getOriginalFilename());
		System.out.println("Dentro do GRAVAR do ImagensController");
		System.out.println("");
		
		if (result.hasErrors()){
			return form();
		}
		
		String baseFolder = "temp";
		String path = fileSaver.write(baseFolder, sumario);
		imagem.setSumarioPath(path);
		System.out.println("Imagem.nomeFoto >>>>  " + imagem.getNomeFoto());
		System.out.println("Imagem.sumarioPath >>>>> " + imagem.getSumarioPath());
		
		imagens.salvar(imagem);
		redirectAttributes.addFlashAttribute("sucesso","Imagem cadastrado com sucesso!");
		return new ModelAndView("redirect:");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar(){
		List<Imagem> imagens = imagemDAO.listar();
		ModelAndView modelAndView = new ModelAndView("imagens/PesquisaImagens");
		modelAndView.addObject("imagens", imagens);
		return modelAndView;
	}
	
	@GetMapping("/pesquisar")
	public ModelAndView pesquisar(){
		ModelAndView mv = new ModelAndView("imagens/PesquisaImagens");
		List<Imagem> imagens = imagemDAO.listar();
		mv.addObject("imagens", imagens);
		
		return mv;
	}
}
