package pl.vgtworld.budget.app.product.pricehistory;

import pl.vgtworld.budget.services.ProductStorageService;
import pl.vgtworld.budget.services.dto.products.ProductDto;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ProductPriceHistoryController {

	@EJB
	private ProductStorageService productStorageService;

	@EJB
	private ProductPriceHistoryRepository productPriceHistoryRepository;

	private Integer productId;

	private ProductDto product;

	private List<ProductPriceHistoryDto> productPriceHistory;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public ProductDto getProduct() {
		return product;
	}

	public List<ProductPriceHistoryDto> getProductPriceHistory() {
		return productPriceHistory;
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
		productPriceHistory = productPriceHistoryRepository.listPriceHistoryForProduct(productId, null);
		return null;
	}

}
