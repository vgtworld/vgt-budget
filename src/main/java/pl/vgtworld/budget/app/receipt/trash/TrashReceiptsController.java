package pl.vgtworld.budget.app.receipt.trash;

import pl.vgtworld.budget.app.receipt.ReceiptRepository;
import pl.vgtworld.budget.app.receipt.ReceiptWithStoreDto;
import pl.vgtworld.budget.services.storage.ReceiptService;
import pl.vgtworld.budget.services.storage.StoreService;

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

	@EJB
	private ReceiptRepository receiptRepository;

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
		deletedReceipts = receiptRepository.listDeletedReceipts();
	}

}
