package pl.vgtworld.budget.app.productlist;

import pl.vgtworld.budget.services.dto.stores.ProductItem;
import pl.vgtworld.budget.services.storage.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class ProductListController {

	@EJB
	private ProductService productService;

	private List<ProductItem> products;

	public List<ProductItem> getProducts() {
		return products;
	}

	public void setProducts(List<ProductItem> products) {
		this.products = products;
	}

	@PostConstruct
	public void loadProducts() {
		products = productService.listAvailableProducts();
	}

}
