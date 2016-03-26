package pl.vgtworld.budget.app.product.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.product.edit.dto.ProductWithTags;
import pl.vgtworld.budget.services.ProductStorageService;
import pl.vgtworld.budget.services.ProductTagStorageService;
import pl.vgtworld.budget.services.TagStorageService;
import pl.vgtworld.budget.services.dto.products.ProductDto;
import pl.vgtworld.budget.services.dto.tags.TagDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProductEditService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEditService.class);

	@EJB
	private ProductStorageService productStorageService;

	@EJB
	private TagStorageService tagStorageService;

	@EJB
	private ProductTagStorageService productTagStorageService;

	public int createNewProduct(ProductWithTags product) {
		LOGGER.debug("Create new product with tags: {}", product);
		ProductDto productServiceDto = new ProductDto();
		productServiceDto.setName(product.getName());
		int productId = productStorageService.createNewProduct(productServiceDto);
		for (String tagName : product.getTags()) {
			linkProductWithTag(productId, tagName);
		}
		return productId;
	}

	public void updateExistingProduct(int productId, ProductWithTags product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(productId);
		productDto.setName(product.getName());
		productStorageService.updateExistingProduct(productDto);
		tagStorageService.deleteForProduct(productId);
		for (String tagName : product.getTags()) {
			linkProductWithTag(productId, tagName);
		}
	}

	private void linkProductWithTag(int productId, String tagName) {
		TagDto tag;
		if (tagStorageService.existWithName(tagName)) {
			tag = tagStorageService.findByName(tagName);
		} else {
			TagDto newTag = new TagDto();
			newTag.setName(tagName);
			tag = tagStorageService.createNewTag(newTag);
		}
		productTagStorageService.createNewLink(productId, tag.getId());
	}

}
