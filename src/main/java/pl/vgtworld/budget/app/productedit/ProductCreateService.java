package pl.vgtworld.budget.app.productedit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.productedit.dto.ProductWithTags;
import pl.vgtworld.budget.services.dto.products.ProductDto;
import pl.vgtworld.budget.services.dto.tags.TagDto;
import pl.vgtworld.budget.services.storage.ProductService;
import pl.vgtworld.budget.services.storage.ProductTagService;
import pl.vgtworld.budget.services.storage.TagService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProductCreateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCreateService.class);

	@EJB
	private ProductService productService;

	@EJB
	private TagService tagService;

	@EJB
	private ProductTagService productTagService;

	public void createNewProduct(ProductWithTags product) {
		LOGGER.debug("Create new product with tags: {}", product);
		ProductDto productServiceDto = new ProductDto();
		productServiceDto.setName(product.getName());
		int productId = productService.createNewProduct(productServiceDto);
		for (String tagName : product.getTags()) {
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

}
