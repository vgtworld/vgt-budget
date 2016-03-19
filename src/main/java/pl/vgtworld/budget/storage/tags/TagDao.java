package pl.vgtworld.budget.storage.tags;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

	public List<Tag> findForProduct(int productId) {
		Query query = em.createNamedQuery(Tag.QUERY_FIND_FOR_PRODUCT);
		query.setParameter("PRODUCT_ID", productId);
		return PersistenceUtil.getResultList(query);
	}

	public void deleteForProduct(int productId) {
		Query query = em.createNamedQuery(Tag.QUERY_DELETE_FOR_PRODUCT);
		query.setParameter("PRODUCT_ID", productId);
		query.executeUpdate();
	}

}
