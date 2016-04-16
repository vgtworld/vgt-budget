package pl.vgtworld.budget.storage.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	public ProductDto findById(String id) {
		LOGGER.debug("Find product by id: {}", id);
		try {
			int convertedId = Integer.parseInt(id);
			return findById(convertedId);
		} catch (NumberFormatException e) {
			LOGGER.debug("Unable to convert product id to integer: {}", id);
			return null;
		}
	}

	public ProductDto findById(int id) {
		LOGGER.debug("Find product by id: {}", id);
		return asProductItem(productDao.findById(id));
	}

	public List<ProductDto> searchProductsByName(String phrase) {
		LOGGER.debug("Search products. phrase:{}", phrase);
		return productDao.searchByName(phrase).stream().map(ProductService::asProductItem).collect(Collectors.toList());
	}

	public List<ProductDto> searchProductsRecentlyBoughtInStore(int storeId, int excludedReceiptId) {
		return productDao
			  .searchRecentlyBoughtInStore(storeId, excludedReceiptId)
			  .stream()
			  .map(ProductService::asProductItem)
			  .collect(Collectors.toList()
			  );
	}

	public int createNewProduct(ProductDto product) {
		LOGGER.debug("Create new product: {}", product);
		Product entity = new Product();
		entity.setName(product.getName());
		entity.setCreatedAt(new Date());
		return productDao.create(entity);
	}

	public List<ProductDto> listAvailableProducts() {
		LOGGER.debug("List available products.");
		return productDao.listAll().stream().map(ProductService::asProductItem).collect(Collectors.toList());
	}

	public void updateExistingProduct(ProductDto product) {
		Product entity = productDao.findById(product.getId());
		entity.setName(product.getName());
	}

	private static ProductDto asProductItem(Product product) {
		if (product == null) {
			return null;
		}
		ProductDto result = new ProductDto();
		result.setId(product.getId());
		result.setName(product.getName());
		return result;
	}

}
