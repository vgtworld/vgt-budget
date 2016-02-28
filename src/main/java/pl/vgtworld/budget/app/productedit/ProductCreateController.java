package pl.vgtworld.budget.app.productedit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.productedit.dto.ProductForm;
import pl.vgtworld.budget.app.productedit.dto.ProductWithTags;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductCreateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCreateController.class);

	@EJB
	private ProductCreateService productCreateService;

	private ProductForm product = new ProductForm();

	public ProductForm getProduct() {
		return product;
	}

	public void setProduct(ProductForm product) {
		this.product = product;
	}

	public String submitForm() {
		LOGGER.debug("Submitted new product form: {}", product);
		productCreateService.createNewProduct(asProductWithTags(product));
		return "product-list?faces-redirect=true";
	}

	private ProductWithTags asProductWithTags(ProductForm product) {
		ProductWithTags dto = new ProductWithTags();
		dto.setName(product.getName());
		dto.setTags(product.getTags());
		return dto;
	}

}
