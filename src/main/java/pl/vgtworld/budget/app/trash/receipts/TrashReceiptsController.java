package pl.vgtworld.budget.app.trash.receipts;

import pl.vgtworld.budget.services.storage.ReceiptService;
import pl.vgtworld.budget.services.storage.StoreService;
import pl.vgtworld.budget.storage.receipts.ReceiptWithStoreDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class TrashReceiptsController {

	@EJB
	private ReceiptService receiptService;

	@EJB
	private StoreService storeService;

	private List<ReceiptWithStoreDto> deletedReceipts;

	public List<ReceiptWithStoreDto> getDeletedReceipts() {
		return deletedReceipts;
	}

	@PostConstruct
	public void init() {
		loadReceiptList();
	}

	public void restoreReceipt(int receiptId) {
		receiptService.restoreFromTrash(receiptId);
		loadReceiptList();
	}

	public void emptyTrash() {
		receiptService.emptyTrash();
		deletedReceipts = new ArrayList<>();
	}

	private void loadReceiptList() {
		deletedReceipts = receiptService.listDeletedReceipts();
	}

}
