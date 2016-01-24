package pl.vgtworld.budget.app.productcreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.productcreate.dto.NewProductWithTags;
import pl.vgtworld.budget.services.dto.products.NewProduct;
import pl.vgtworld.budget.services.dto.tags.NewTag;
import pl.vgtworld.budget.services.dto.tags.TagItem;
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

	public void createNewProduct(NewProductWithTags product) {
		LOGGER.debug("Create new product with tags: {}", product);
		NewProduct productServiceDto = new NewProduct();
		productServiceDto.setName(product.getName());
		int productId = productService.createNewProduct(productServiceDto);
		for (String tagName : product.getTags()) {
			TagItem tag;
			if (tagService.existWithName(tagName)) {
				tag = tagService.findByName(tagName);
			} else {
				NewTag newTag = new NewTag();
				newTag.setName(tagName);
				tag = tagService.createNewTag(newTag);
			}
			productTagService.createNewLink(productId, tag.getId());
		}
	}

}
