package pl.vgtworld.budget.storage.producttags;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductTagDao {

	@PersistenceContext
	private EntityManager em;

	public void create(ProductTag productTag) {
		em.persist(productTag);
	}
}
