package pl.vgtworld.budget.app.receipt.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receipt.edit.dto.ReceiptForm;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.services.dto.stores.StoreDto;
import pl.vgtworld.budget.services.storage.ReceiptService;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
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

	private Integer receiptId;

	public ReceiptForm getReceipt() {
		return receipt;
	}

	public void setReceipt(ReceiptForm receipt) {
		this.receipt = receipt;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Map<String, Integer> getAvailableStores() {
		List<StoreDto> stores = storeService.listAvailableStores();
		Map<String, Integer> result = new LinkedHashMap<>();
		result.put("Choose store...", null);
		for (StoreDto store : stores) {
			String label = String.format("%s - %s - %s", store.getName(), store.getCity(), store.getAddress());
			result.put(label, store.getId());
		}
		return result;
	}

	public String submitForm() {
		LOGGER.debug("Submitted receipt form: {}", receipt);
		ReceiptDto dto = asReceiptDto(this.receipt);
		if (receiptId == null) {
			receiptService.createNewReceipt(dto);
		} else {
			dto.setId(receiptId);
			receiptService.updateReceipt(dto);
		}
		return "receipt-list?faces-redirect=true";
	}

	public void initData() {
		if (receiptId != null) {
			ReceiptDto dto = receiptService.findById(receiptId);
			if (dto != null) {
				receipt.setStoreId(dto.getStoreId());
				receipt.setPurchaseDate(dto.getPurchaseDate());
				return;
			}
			LOGGER.debug("Receipt with provided id does not exist. ID:{}", receiptId);
			receiptId = null;
			return;
		}
		LOGGER.debug("Receipt id not provided. Switching to create receipt flow.");
		receipt.setPurchaseDate(new Date());
	}

	private ReceiptDto asReceiptDto(ReceiptForm receipt) {
		ReceiptDto dto = new ReceiptDto();
		dto.setStoreId(receipt.getStoreId());
		dto.setPurchaseDate(receipt.getPurchaseDate());
		return dto;
	}
}
