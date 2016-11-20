package br.com.ufrn.bti.dao;

import org.hibernate.Query;

import br.com.ufrn.bti.dominio.CartaoBancario;
import br.com.ufrn.bti.dominio.Pessoa;
import br.com.ufrn.bti.util.HibernateUtil;

public class CartaoBancarioDAO extends GenericDAO {

	public CartaoBancarioDAO() {
	}

	public CartaoBancario getCartaoDa(Pessoa pessoa) {
		String hql = "select c from CartaoBancario c WHERE c.pessoa.cpf= :cpf";
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
		
		Query query = session.createQuery(hql);
		query.setString("cpf", pessoa.getCpf().replaceAll("\\D", ""));

		CartaoBancario cartaoEncontrado = (CartaoBancario) query.uniqueResult();
		
		session.close();

		if (cartaoEncontrado == null) {
			return null;
		}

		return cartaoEncontrado;
	}

}
