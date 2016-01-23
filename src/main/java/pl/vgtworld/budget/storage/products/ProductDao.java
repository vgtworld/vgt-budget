package pl.vgtworld.budget.storage.products;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductDao {

	@PersistenceContext
	private EntityManager em;

	public int create(Product product) {
		em.persist(product);
		return product.getId();
	}

}
