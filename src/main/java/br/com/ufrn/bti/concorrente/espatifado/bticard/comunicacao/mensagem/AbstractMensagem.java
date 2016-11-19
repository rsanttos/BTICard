package br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.mensagem;

import java.io.Serializable;

public abstract class AbstractMensagem<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5166784794777061808L;
	private TipoMensagem tipoMensagem;
	private T conteudo;
	
	public AbstractMensagem(TipoMensagem tipoMensagem, T conteudo) {
		this.tipoMensagem = tipoMensagem;
		this.conteudo = conteudo;
	}
	
	public AbstractMensagem(){}

	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}

	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}

	public T getConteudo() {
		return conteudo;
	}

	public void setConteudo(T conteudo) {
		this.conteudo = conteudo;
	}

}
