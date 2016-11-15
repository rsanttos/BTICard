package br.com.ufrn.bti.concorrente.espatifado.bticard.comunicacao;

import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.Pessoa;

public class MensagemRequisicao {
	
	private Pessoa pessoa;
	private double valorPagamento;

	public MensagemRequisicao(Pessoa pessoa, double valorPagamento) {
		this.pessoa = pessoa;
		this.valorPagamento = valorPagamento;
	}

	public MensagemRequisicao() {
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
