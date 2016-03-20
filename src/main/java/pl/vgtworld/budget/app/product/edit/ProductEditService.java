package pl.vgtworld.budget.app.product.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.product.edit.dto.ProductWithTags;
import pl.vgtworld.budget.services.dto.products.ProductDto;
import pl.vgtworld.budget.services.dto.tags.TagDto;
import pl.vgtworld.budget.services.storage.ProductService;
import pl.vgtworld.budget.services.storage.ProductTagService;
import pl.vgtworld.budget.services.storage.TagService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProductEditService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEditService.class);

	@EJB
	private ProductService productService;

	@EJB
	private TagService tagService;

	@EJB
	private ProductTagService productTagService;

	public int createNewProduct(ProductWithTags product) {
		LOGGER.debug("Create new product with tags: {}", product);
		ProductDto productServiceDto = new ProductDto();
		productServiceDto.setName(product.getName());
		int productId = productService.createNewProduct(productServiceDto);
		for (String tagName : product.getTags()) {
			linkProductWithTag(productId, tagName);
		}
		return productId;
	}

	public void updateExistingProduct(int productId, ProductWithTags product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(productId);
		productDto.setName(product.getName());
		productService.updateExistingProduct(productDto);
		tagService.deleteForProduct(productId);
		for (String tagName : product.getTags()) {
			linkProductWithTag(productId, tagName);
		}
	}

	private void linkProductWithTag(int productId, String tagName) {
		TagDto tag;
		if (tagService.existWithName(tagName)) {
			tag = tagService.findByName(tagName);
		} else {
			TagDto newTag = new TagDto();
			newTag.setName(tagName);
			tag = tagService.createNewTag(newTag);
		}
		productTagService.createNewLink(productId, tag.getId());
	}

}
