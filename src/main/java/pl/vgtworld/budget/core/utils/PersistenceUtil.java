package pl.vgtworld.budget.core.utils;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public final class PersistenceUtil {

	private PersistenceUtil() {
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getResultList(Query query) {
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static <E> E getSingleResult(Query query) {
		query.setMaxResults(2);
		List<E> results = query.getResultList();
		if (results.size() == 2) {
			throw new NonUniqueResultException();
		}
		if (results.size() == 1) {
			return results.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <E> E getFirstResult(Query query) {
		query.setMaxResults(1);
		List<E> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}

}
