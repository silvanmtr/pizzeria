package br.com.pizzeria.repository.helper.pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.pizzeria.model.Pizza;
import br.com.pizzeria.repository.filter.PizzaFilter;
import br.com.pizzeria.repository.paginacao.PaginacaoUtil;

public class PizzasImpl implements PizzasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Pizza> filtrar(PizzaFilter filtro, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pizza.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(PizzaFilter filtro, Criteria criteria) {
		if(filtro != null){
			
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if (filtro.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", filtro.getSabor()));
			}
			
			if (isTamanhoPresente(filtro)) {
				criteria.add(Restrictions.eq("tamanho", filtro.getTamanho()));
			}

			if (filtro.getValorDe() != null) {
				criteria.add(Restrictions.ge("preco", filtro.getValorDe()));
			}

			if (filtro.getValorAte() != null) {
				criteria.add(Restrictions.le("preco", filtro.getValorAte()));
			}
		}
	}
	
	private Long total(PizzaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pizza.class);
		adicionarFiltro(filtro, criteria);

		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private boolean isTamanhoPresente(PizzaFilter filtro) {
		return filtro.getTamanho() != null && filtro.getTamanho().getCodigo() != null;
	}

	@Transactional(readOnly = true)
	@Override
	public Pizza buscarPizzas(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pizza.class);
		criteria.add(Restrictions.eq("codigo", codigo));
		
		return (Pizza) criteria.uniqueResult();
	}

}
