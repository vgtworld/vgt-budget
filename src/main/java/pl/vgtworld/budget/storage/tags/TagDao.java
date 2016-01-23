package pl.vgtworld.budget.storage.tags;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TagDao {

	@PersistenceContext
	private EntityManager em;

	public int create(Tag tag) {
		em.persist(tag);
		return tag.getId();
	}

	public Tag findByName(String name) {
		Query query = em.createNamedQuery(Tag.QUERY_FIND_BY_NAME);
		query.setParameter("NAME", name);
		return PersistenceUtil.getSingleResult(query);
	}

}
