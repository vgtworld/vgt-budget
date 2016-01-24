package pl.vgtworld.budget.services.storage;

import pl.vgtworld.budget.services.dto.products.ProductItem;
import pl.vgtworld.budget.storage.products.Product;
import pl.vgtworld.budget.storage.products.ProductDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductService {

	@EJB
	private ProductDao productDao;

	public List<ProductItem> listAvailableProducts() {
		return productDao.listAll().stream().map(ProductService::asProductItem).collect(Collectors.toList());
	}

	private static ProductItem asProductItem(Product product) {
		if (product == null) {
			return null;
		}
		ProductItem result = new ProductItem();
		result.setId(product.getId());
		result.setName(product.getName());
		return result;
	}

}
