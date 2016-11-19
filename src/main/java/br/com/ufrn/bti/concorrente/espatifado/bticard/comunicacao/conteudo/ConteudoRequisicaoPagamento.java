package br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao.conteudo;

import java.io.Serializable;

import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.Pessoa;

public class ConteudoRequisicaoPagamento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5018130925568969499L;
	private Pessoa pessoa;
	private double valorPagamento;

	public ConteudoRequisicaoPagamento(Pessoa pessoa, double valorPagamento) {
		this.pessoa = pessoa;
		this.valorPagamento = valorPagamento;
	}

	public ConteudoRequisicaoPagamento() {
		// TODO Auto-generated constructor stub
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public double getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

}
