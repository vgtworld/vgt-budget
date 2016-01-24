package pl.vgtworld.budget.app.productlist;

import pl.vgtworld.budget.app.productlist.dto.ProductWithTags;
import pl.vgtworld.budget.services.dto.products.ProductItem;
import pl.vgtworld.budget.services.storage.ProductService;
import pl.vgtworld.budget.services.storage.TagService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
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
		List<ProductItem> availableProducts = productService.listAvailableProducts();
		products = new ArrayList<>(availableProducts.size());
		for (ProductItem availableProduct : availableProducts) {
			ProductWithTags product = new ProductWithTags();
			product.setProduct(availableProduct);
			product.setTags(tagService.findForProduct(availableProduct.getId()));
			products.add(product);
		}
	}

}
