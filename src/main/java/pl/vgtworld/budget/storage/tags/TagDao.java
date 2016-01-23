package pl.vgtworld.budget.storage.tags;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TagDao {

	@PersistenceContext
	private EntityManager em;

	public int create(Tag tag) {
		em.persist(tag);
		return tag.getId();
	}

}
