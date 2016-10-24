var Pizzaria = Pizzaria || {};

Pizzaria.tamanhoCadastroRapido = (function() {
	
	function tamanhoCadastroRapido() {
		this.modal = $('#modalCadastroRapidoTamanho');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-tamanho-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNometamanho = $('#nomeTamanho');
		this.inputQuantidadePedaco = $('#quantidadePedaco');
		
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-tamanho');
	}
	
	tamanhoCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNometamanho.focus();
	}
	
	function onModalClose() {
		this.inputNometamanho.val('');
		this.inputQuantidadePedaco.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nometamanho = this.inputNometamanho.val().trim();
		var quantidadePedaco = this.inputQuantidadePedaco.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nometamanho, pedaco: quantidadePedaco }),
			error: onErroSalvandotamanho.bind(this),
			success: ontamanhoSalvo.bind(this)
		});
	}
	
	function onErroSalvandotamanho(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function ontamanhoSalvo(tamanho) {
		var combotamanho = $('#tamanho');
		combotamanho.append('<option value=' + tamanho.codigo + '>' + tamanho.nome + '</option>');
		combotamanho.val(tamanho.codigo);
		this.modal.modal('hide');
	}
	
	return tamanhoCadastroRapido;
	
}());

$(function() {
	var tamanhoCadastroRapido = new Pizzaria.tamanhoCadastroRapido();
	tamanhoCadastroRapido.iniciar();
});
