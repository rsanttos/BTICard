package br.com.ufrn.bti.concorrente.espatifado.bticard.dao;

import org.hibernate.Query;

import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.CartaoBancario;
import br.com.ufrn.bti.concorrente.espatifado.bticard.dominio.Pessoa;
import br.com.ufrn.bti.concorrente.espatifado.bticard.util.HibernateUtil;

public class CartaoBancarioDAO extends GenericDAO {

	public CartaoBancarioDAO() {
	}

	public double getLimiteCartao(Pessoa pessoa) {
		String hql = "select c from CartaoBancario c, Pessoa p  WHERE c.p.cpf= :cpf";
		Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString("cpf", pessoa.getCpf().replaceAll("\\D", ""));

		CartaoBancario cartaoEncontrado = (CartaoBancario) query.uniqueResult();

		if (cartaoEncontrado == null) {
			return -1;
		}

		return cartaoEncontrado.getLimite();
	}

}
