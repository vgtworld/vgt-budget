package pl.vgtworld.budget.app.receipt.product.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.storage.products.ProductService;
import pl.vgtworld.budget.storage.receipts.ReceiptService;
import pl.vgtworld.budget.storage.products.ProductDto;
import pl.vgtworld.budget.storage.receipts.ReceiptDto;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ReceiptProductSearchController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptProductSearchController.class);

	@EJB
	private ReceiptService receiptService;

	@EJB
	private ProductService productService;

	private Integer receiptId;

	private String searchPhrase;

	private List<ProductDto> productsSearch;

	private List<ProductDto> recentlyBought;

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public List<ProductDto> getProductsSearch() {
		return productsSearch;
	}

	public void searchProducts() {
		LOGGER.debug("Search products. Phrase:{}", searchPhrase);
		productsSearch = productService.searchProductsByName(searchPhrase);
		LOGGER.debug("Products found: {}", productsSearch.size());
	}

	public List<ProductDto> getRecentlyBought() {
		return recentlyBought;
	}

	public String initData() {
		if (receiptId != null) {
			ReceiptDto receipt = receiptService.findById(receiptId);
			if (receipt == null) {
				LOGGER.debug("Receipt with provided id does not exist. ID:{}", receiptId);
				return "receipt-list?faces-redirect=true";
			}
			recentlyBought = productService.searchProductsRecentlyBoughtInStore(receipt.getStoreId(), receiptId);
			return null;
		}
		LOGGER.warn("Receipt id not available. Unable to fill form.");
		return "receipt-list?faces-redirect=true";
	}

}
