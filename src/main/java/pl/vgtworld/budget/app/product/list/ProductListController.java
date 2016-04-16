package pl.vgtworld.budget.app.product.list;

import pl.vgtworld.budget.app.product.list.dto.ProductWithTags;
import pl.vgtworld.budget.services.ProductService;
import pl.vgtworld.budget.services.TagService;
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
	private ProductService productService;

	@EJB
	private TagService tagService;

	private List<ProductWithTags> products;

	public List<ProductWithTags> getProducts() {
		return products;
	}

	public void setProducts(List<ProductWithTags> products) {
		this.products = products;
	}

	@PostConstruct
	public void loadProducts() {
		List<ProductDto> availableProducts = productService.listAvailableProducts();
		products = new ArrayList<>(availableProducts.size());
		for (ProductDto availableProduct : availableProducts) {
			ProductWithTags product = new ProductWithTags();
			product.setProduct(availableProduct);
			product.setTags(tagService.findForProduct(availableProduct.getId()));
			products.add(product);
		}
	}

}
