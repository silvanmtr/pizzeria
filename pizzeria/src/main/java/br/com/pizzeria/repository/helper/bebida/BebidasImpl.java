package br.com.pizzeria.repository.helper.bebida;

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

import br.com.pizzeria.model.Bebida;
import br.com.pizzeria.repository.filter.BebidaFilter;
import br.com.pizzeria.repository.paginacao.PaginacaoUtil;

public class BebidasImpl implements BebidasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Bebida> filtrar(BebidaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Bebida.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(BebidaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Bebida.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(BebidaFilter filtro, Criteria criteria) {
		if (filtro != null) {
				
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}
    
	@Transactional(readOnly = true)
	@Override
	public Bebida buscarBebidas(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Bebida.class);
		criteria.add(Restrictions.eq("codigo", codigo));
		
		return (Bebida) criteria.uniqueResult();
	}

}
