package br.com.ufrn.bti.concorrente.espatifado.bticard.servico;

import br.com.ufrn.bti.concorrente.espatifado.bticard.dao.CartaoBancarioDAO;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.CartaoBancario;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.Pessoa;

public class PagamentoServico {
	
	private CartaoBancarioDAO dao;

	public PagamentoServico() {
		dao = new CartaoBancarioDAO();
	}
	
	public boolean efetuarPagamento(Pessoa pessoa, double valorPagamento){
		CartaoBancario cartao = dao.getCartaoDa(pessoa);
		
		if(cartao == null){
			System.out.println("Cartão Não existe!");
			return false;
		} else if((cartao.getSaldoDevedor() + valorPagamento) > cartao.getLimite()){
			System.out.println("saldo: " + cartao.getSaldoDevedor());
			System.out.println("valor: " + valorPagamento);
			System.out.println("limite: " + cartao.getLimite());
			return false;
		}
		
		cartao.setSaldoDevedor(cartao.getSaldoDevedor() + valorPagamento);
		dao.salvarOuAtualizar(cartao);
		
		return true;
	}
}
