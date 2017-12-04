package org.gestormultimidia.validation;

import org.gestormultimidia.model.Imagem;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class ImagemValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Imagem.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		
	}
}
