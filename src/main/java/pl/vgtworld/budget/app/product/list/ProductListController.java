package pl.vgtworld.budget.app.product.list;

import pl.vgtworld.budget.app.product.list.dto.ProductWithTags;
import pl.vgtworld.budget.services.ProductStorageService;
import pl.vgtworld.budget.services.TagStorageService;
import pl.vgtworld.budget.services.dto.products.ProductDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ProductListController {

	@EJB
	private ProductStorageService productStorageService;

	@EJB
	private TagStorageService tagStorageService;

	private List<ProductWithTags> products;

	public List<ProductWithTags> getProducts() {
		return products;
	}

	public void setProducts(List<ProductWithTags> products) {
		this.products = products;
	}

	@PostConstruct
	public void loadProducts() {
		List<ProductDto> availableProducts = productStorageService.listAvailableProducts();
		products = new ArrayList<>(availableProducts.size());
		for (ProductDto availableProduct : availableProducts) {
			ProductWithTags product = new ProductWithTags();
			product.setProduct(availableProduct);
			product.setTags(tagStorageService.findForProduct(availableProduct.getId()));
			products.add(product);
		}
	}

}
