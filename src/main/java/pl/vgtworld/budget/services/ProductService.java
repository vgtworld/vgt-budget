package pl.vgtworld.budget.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.products.NewProduct;
import pl.vgtworld.budget.services.storage.ProductTagService;
import pl.vgtworld.budget.storage.products.Product;
import pl.vgtworld.budget.storage.products.ProductDao;
import pl.vgtworld.budget.storage.producttags.ProductTagDao;
import pl.vgtworld.budget.storage.tags.Tag;
import pl.vgtworld.budget.storage.tags.TagDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@EJB
	private ProductDao productDao;

	@EJB
	private TagDao tagDao;

	@EJB
	private ProductTagService productTagService;

	public void createNewProduct(NewProduct product) {
		Product productEntity = new Product();
		productEntity.setName(product.getName());
		productDao.create(productEntity);
		LOGGER.debug("Created product with id:{}, name:{}", productEntity.getId(), productEntity.getName());
		List<Integer> tagIds = getTagIds(product.getTags());
		for (Integer tagId : tagIds) {
			linkProductToTag(productEntity.getId(), tagId);
		}
	}

	private void linkProductToTag(int productId, int tagId) {
		productTagService.createNewLink(productId, tagId);
	}

	private List<Integer> getTagIds(List<String> tags) {
		return tags.stream().map(this::getTagId).collect(Collectors.toList());
	}

	private int getTagId(String name) {
		Tag tag = tagDao.findByName(name);
		if (tag == null) {
			tag = new Tag();
			tag.setName(name);
			tagDao.create(tag);
			LOGGER.debug("Created tag with id:{}, name:{}", tag.getId(), tag.getName());
		}
		return tag.getId();
	}
}
