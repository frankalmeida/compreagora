package br.Pedido.Filtro;

import java.util.List;

import javax.persistence.EntityManager;

import org.primefaces.model.SortOrder;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

import br.Pedido.Pedido;

public class PedidoWithFilterByIdEmpresa implements IFiltroPedido {

	@Override
	public List<Pedido> buscaPorPaginacao(EntityManager em, int startingAt,
			int maxPerPage, int id, String sortFiel, SortOrder sortOrder) {
		EasyCriteria<Pedido> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Pedido.class);
		easyCriteria.innerJoin("empresa").andEquals("empresa.idEmpresa", id);

		switch (sortOrder.ordinal()) {

		case 0:
			easyCriteria.orderByAsc(sortFiel);
			break;

		case 1:
			easyCriteria.orderByDesc(sortFiel);
			break;

		case -1:
			break;

		default:
			break;
		}

		easyCriteria.setFirstResult(startingAt);
		easyCriteria.setMaxResults(maxPerPage);

		return easyCriteria.getResultList();

	}

	@Override
	public int countPedido(EntityManager em, int id) {
		EasyCriteria<Pedido> easyCriteria = EasyCriteriaFactory
				.createQueryCriteria(em, Pedido.class);
		easyCriteria.innerJoin("empresa").andEquals("empresa.idEmpresa", id)
				.orderByAsc("statusPedido");
		List<Pedido> pedidos = easyCriteria.getResultList();
		if (pedidos == null) {
			return 0;
		}
		return pedidos.size();
	}

}
