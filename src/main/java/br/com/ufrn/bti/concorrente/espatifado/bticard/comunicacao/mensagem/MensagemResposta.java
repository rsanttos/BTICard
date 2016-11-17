package br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.mensagem;

public class MensagemResposta<T> extends AbstractMensagem<T> {

	public MensagemResposta(TipoMensagem tipoMensagem, T conteudo, boolean sucesso) {
		super(tipoMensagem, conteudo);
		this.sucesso = sucesso;
	}
	
	public MensagemResposta(){}

	private boolean sucesso;
	
	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

}
