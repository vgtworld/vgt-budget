package pl.vgtworld.budget.app.receipt.list;

import pl.vgtworld.budget.app.receipt.ReceiptRepository;
import pl.vgtworld.budget.app.receipt.ReceiptWithStoreDto;
import pl.vgtworld.budget.services.storage.ReceiptService;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ReceiptListController {

	@EJB
	private ReceiptService receiptService;

	@EJB
	private StoreService storeService;

	@EJB
	private ReceiptRepository receiptRepository;

	private List<ReceiptWithStoreDto> receiptList;

	public List<ReceiptWithStoreDto> getReceiptList() {
		return receiptList;
	}

	@PostConstruct
	public void init() {
		loadReceiptList();
	}

	public void moveReceiptToTrash(int receiptId) {
		receiptService.moveToTrash(receiptId);
		loadReceiptList();
	}

	private void loadReceiptList() {
		receiptList = receiptRepository.listNewestReceipts();
	}

}
