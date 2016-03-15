package pl.vgtworld.budget.app.receiptproductlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receiptproductlist.dto.AddedProductDto;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.services.dto.stores.StoreDto;
import pl.vgtworld.budget.services.storage.ReceiptService;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ReceiptProductListController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptProductListController.class);

	@EJB
	private ReceiptService receiptService;

	@EJB
	private StoreService storeService;

	@EJB
	private ReceiptProductListService receiptProductListService;

	private ReceiptDto receipt;

	private StoreDto store;

	private Integer receiptId;

	private List<AddedProductDto> products = new ArrayList<>();

	public ReceiptDto getReceipt() {
		return receipt;
	}

	public void setReceipt(ReceiptDto receipt) {
		this.receipt = receipt;
	}

	public StoreDto getStore() {
		return store;
	}

	public void setStore(StoreDto store) {
		this.store = store;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public List<AddedProductDto> getProducts() {
		return products;
	}

	public String initData() {
		LOGGER.debug("Init data");
		if (receiptId != null) {
			receipt = receiptService.findById(receiptId);
			if (receipt != null) {
				store = storeService.findById(receipt.getStoreId());
				products = receiptProductListService.findProductsForReceipt(receiptId);
				return null;
			}
			LOGGER.debug("Receipt with provided id does not exist. ID:{}", receiptId);
			receiptId = null;
			receipt = null;
			store = null;
			return "receipt-list?faces-redirect=true";
		}
		LOGGER.warn("Receipt id not available. Unable to fill form.");
		return "receipt-list?faces-redirect=true";
	}
}
