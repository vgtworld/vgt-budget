package pl.vgtworld.budget.storage.producttags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProductTagService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTagService.class);

	@EJB
	private ProductTagDao productTagDao;

	public void createNewLink(int productId, int tagId) {
		LOGGER.debug("Create new link between product:{} and tag:{}", productId, tagId);
		ProductTagPK id = new ProductTagPK();
		id.setProductId(productId);
		id.setTagId(tagId);
		ProductTag entity = new ProductTag();
		entity.setId(id);
		productTagDao.create(entity);
	}

}
