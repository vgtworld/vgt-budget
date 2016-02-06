package pl.vgtworld.budget.services.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.products.NewProduct;
import pl.vgtworld.budget.services.dto.products.ProductItem;
import pl.vgtworld.budget.storage.products.Product;
import pl.vgtworld.budget.storage.products.ProductDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@EJB
	private ProductDao productDao;

	public ProductItem findById(String id) {
		try {
			int convertedId = Integer.parseInt(id);
			return findById(convertedId);
		} catch (NumberFormatException e) {
			LOGGER.debug("Unable to convert product id to integer: {}", id);
			return null;
		}
	}

	public ProductItem findById(int id) {
		LOGGER.debug("Find by id: {}", id);
		return asProductItem(productDao.findById(id));
	}

	public int createNewProduct(NewProduct product) {
		LOGGER.debug("Create new product: {}", product);
		Product entity = new Product();
		entity.setName(product.getName());
		entity.setCreatedAt(new Date());
		return productDao.create(entity);
	}

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
