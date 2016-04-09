package pl.vgtworld.budget.app.product.details;

import pl.vgtworld.budget.services.ProductStorageService;
import pl.vgtworld.budget.services.dto.products.ProductDto;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductDetailsController {

	@EJB
	private ProductStorageService productStorageService;

	private Integer productId;

	private ProductDto product;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public ProductDto getProduct() {
		return product;
	}

	public String initData() {
		final String mainPageRedirect = "index?faces-redirect=true";
		if (productId == null) {
			return mainPageRedirect;
		}
		product = productStorageService.findById(productId);
		if (product == null) {
			return mainPageRedirect;
		}
		return null;
	}

}
