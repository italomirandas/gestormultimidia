var Produto = Produto || {};

Produto.UploadFoto = (function(){
	this.token = $("meta[name='_csrf']").attr("content");
  	this.header = $("meta[name='_csrf_header']").attr("content");
	
	function UploadFoto(){
		this.inputNomeFoto = $('input[name=nomeFoto]');
		this.inputContentType = $('input[name=contentType]');
		
		this.htmlFotoCervejaTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(this.htmlFotoCervejaTemplate);
		
		this.containerFotoCerveja = $('.js-container-foto-cerveja');
		
		this.uploadDrop = $('#upload-drop');
		
		
	}
	
	UploadFoto.prototype.iniciar = function () {
		var settings = {
				type : 'json',
				filelimit : 1,
				allow : '*.(jpg|jpeg|png)',
				action : '/gestormultimidia/fotos',
				beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				complete: onUploadCompleto.bind(this)
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if (this.inputNomeFoto.val()){
			onUploadCompleto.call(this, {nomeFoto: this.inputNomeFoto.val(), contentType: this.inputContentType.val()});
		}
		
	}
	
	function onUploadCompleto(resposta){
		this.inputNomeFoto.val( resposta.nomeFoto);
		this.inputContentType.val( resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		var htmlFotoCerveja = this.template( {nomeFoto: resposta.nomeFoto});
		this.containerFotoCerveja.append(htmlFotoCerveja);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
		
	}
	
	function onRemoverFoto(){
		
		$('.js-foto-cerveja').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}
	
	return UploadFoto;
	
})();

$(function() {
	
	var uploadFoto = new Produto.UploadFoto();
	uploadFoto.iniciar();
	
	
});

