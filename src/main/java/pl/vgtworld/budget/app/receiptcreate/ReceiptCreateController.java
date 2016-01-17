package pl.vgtworld.budget.app.receiptcreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receiptcreate.dto.NewReceiptForm;
import pl.vgtworld.budget.app.receiptcreate.dto.ReceiptStore;
import pl.vgtworld.budget.services.dto.stores.StoreItem;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class ReceiptCreateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptCreateController.class);

	private NewReceiptForm receipt = new NewReceiptForm();

	@EJB
	private StoreService storeService;

	public NewReceiptForm getReceipt() {
		return receipt;
	}

	public void setReceipt(NewReceiptForm receipt) {
		this.receipt = receipt;
	}

	public List<StoreItem> listAvailableStores() {
		return storeService.listAllStores();
	}

	public String chooseStore(String storeId) {
		LOGGER.debug("Store with id: {} chosen for receipt.", storeId);
		StoreItem store = storeService.findById(storeId);
		if (store == null) {
			LOGGER.debug("Unable to find store with provided id.");
			//TODO Prepare and display error message.
			return null;
		}
		ReceiptStore chosenStore = new ReceiptStore();
		chosenStore.setId(store.getId());
		chosenStore.setName(store.getName());
		chosenStore.setCity(store.getCity());
		chosenStore.setAddress(store.getAddress());
		receipt.setStore(chosenStore);
		return "receipt-create";
	}

}
