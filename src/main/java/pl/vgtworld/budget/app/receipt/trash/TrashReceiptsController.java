package pl.vgtworld.budget.app.receipt.trash;

import pl.vgtworld.budget.app.receipt.ReceiptRepository;
import pl.vgtworld.budget.app.receipt.ReceiptWithStoreDto;
import pl.vgtworld.budget.services.ReceiptStorageService;
import pl.vgtworld.budget.services.StoreStorageService;

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
	private ReceiptStorageService receiptStorageService;

	@EJB
	private StoreStorageService storeStorageService;

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
		receiptStorageService.restoreFromTrash(receiptId);
		loadReceiptList();
	}

	public void emptyTrash() {
		receiptStorageService.emptyTrash();
		deletedReceipts = new ArrayList<>();
	}

	private void loadReceiptList() {
		deletedReceipts = receiptRepository.listDeletedReceipts();
	}

}
