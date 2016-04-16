package pl.vgtworld.budget.app.product.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.product.edit.dto.ProductForm;
import pl.vgtworld.budget.app.product.edit.dto.ProductWithTags;
import pl.vgtworld.budget.storage.products.ProductService;
import pl.vgtworld.budget.storage.tags.TagService;
import pl.vgtworld.budget.storage.products.ProductDto;
import pl.vgtworld.budget.storage.tags.TagDto;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ProductEditController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEditController.class);

	@EJB
	private ProductEditService productEditService;

	@EJB
	private ProductService productService;

	@EJB
	private TagService tagService;

	private ProductForm product = new ProductForm();

	private Integer productId;

	private Integer receiptId;

	public ProductForm getProduct() {
		return product;
	}

	public void setProduct(ProductForm product) {
		this.product = product;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public String initData() {
		if (isEditProductFlow()) {
			ProductDto productDto = productService.findById(productId);
			if (productDto != null) {
				List<TagDto> productTags = tagService.findForProduct(productId);
				product.setName(productDto.getName());
				product.setTags(productTags.stream().map(TagDto::getName).collect(Collectors.toList()));
				return null;
			}
			LOGGER.debug("Product with provided id does not exist. ID:{}", productId);
			return "product-list?faces-redirect=true";
		}
		LOGGER.debug("Product id not provided. Switching to new product flow.");
		return null;
	}

	public String submitForm() {
		LOGGER.debug("Submitted product form: {}", product);
		if (isCreateProductFlow()) {
			int newProductId = productEditService.createNewProduct(asProductWithTags(product));
			if (receiptId != null) {
				return String.format(
					  "receipt-product-edit?productId=%s&receiptId=%s&faces-redirect=true",
					  newProductId,
					  receiptId
				);
			}
			return "product-list?faces-redirect=true";
		}
		if (isEditProductFlow()) {
			productEditService.updateExistingProduct(productId, asProductWithTags(product));
			return "product-list?faces-redirect=true";
		}
		LOGGER.warn("Invalid parameters. Unable to save form. form:{}", product);
		return "product-list?faces-redirect=true";
	}

	private ProductWithTags asProductWithTags(ProductForm product) {
		ProductWithTags dto = new ProductWithTags();
		dto.setName(product.getName());
		dto.setTags(product.getTags());
		return dto;
	}

	private boolean isEditProductFlow() {
		return productId != null;
	}

	private boolean isCreateProductFlow() {
		return !isEditProductFlow();
	}

}
