package br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao;

public class MensagemResposta {

	private boolean sucesso;
	
	public MensagemResposta(boolean sucesso) {
		this.sucesso = sucesso;
	}
	
	public boolean deuCerto(){
		return this.sucesso;
	}

}
