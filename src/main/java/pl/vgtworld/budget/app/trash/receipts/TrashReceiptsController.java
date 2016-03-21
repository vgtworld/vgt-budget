package pl.vgtworld.budget.app.trash.receipts;

import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.services.dto.receipts.ReceiptWithStoreDto;
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

	private void loadReceiptList() {
		//TODO Code similar to ReceiptListController::loadReceiptsList. Extract to common service.
		List<ReceiptDto> receiptDtos = receiptService.listDeletedReceipts();
		deletedReceipts = new ArrayList<>();
		for (ReceiptDto receiptDto : receiptDtos) {
			ReceiptWithStoreDto receiptWithStore = new ReceiptWithStoreDto();
			receiptWithStore.setId(receiptDto.getId());
			receiptWithStore.setPurchaseDate(receiptDto.getPurchaseDate());
			receiptWithStore.setTotalAmount(receiptDto.getTotalAmount());
			receiptWithStore.setStore(storeService.findById(receiptDto.getStoreId()).getName());
			deletedReceipts.add(receiptWithStore);
		}
	}

}
