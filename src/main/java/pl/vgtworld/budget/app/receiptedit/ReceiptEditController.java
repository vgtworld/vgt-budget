package pl.vgtworld.budget.app.receiptedit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receiptedit.dto.ReceiptForm;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.services.dto.stores.StoreDto;
import pl.vgtworld.budget.services.storage.ReceiptService;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class ReceiptEditController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptEditController.class);

	@EJB
	private StoreService storeService;

	@EJB
	private ReceiptService receiptService;

	private ReceiptForm receipt = new ReceiptForm();

	public ReceiptForm getReceipt() {
		return receipt;
	}

	public void setReceipt(ReceiptForm receipt) {
		this.receipt = receipt;
	}

	public Map<String, Integer> getAvailableStores() {
		List<StoreDto> stores = storeService.listAvailableStores();
		Map<String, Integer> result = new LinkedHashMap<>();
		result.put("Choose store...", null);
		for (StoreDto store : stores) {
			result.put(store.getName(), store.getId());
		}
		return result;
	}

	public String submitForm() {
		LOGGER.debug("Submitted receipt form: {}", receipt);
		receiptService.createNewReceipt(asReceiptDto(receipt));
		return "receipt-list?faces-redirect=true";
	}

	private ReceiptDto asReceiptDto(ReceiptForm receipt) {
		ReceiptDto dto = new ReceiptDto();
		dto.setStoreId(receipt.getStoreId());
		dto.setPurchaseDate(receipt.getPurchaseDate());
		return dto;
	}
}
