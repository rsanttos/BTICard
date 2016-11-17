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
			return false;
		} else if((cartao.getSaldoDevedor() + valorPagamento) > cartao.getLimite()){
			return false;
		}
		
		cartao.setSaldoDevedor(cartao.getSaldoDevedor() + valorPagamento);
		dao.salvarOuAtualizar(cartao);
		
		return true;
	}
}
