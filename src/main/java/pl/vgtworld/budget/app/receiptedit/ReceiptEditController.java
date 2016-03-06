package pl.vgtworld.budget.app.receiptedit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receiptedit.dto.ReceiptForm;
import pl.vgtworld.budget.services.dto.stores.StoreDto;
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

	public void submitForm() {
		LOGGER.debug("Submitted receipt form: {}", receipt);
	}
}
