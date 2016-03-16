package pl.vgtworld.budget.app.receipt.list;

import pl.vgtworld.budget.app.receipt.list.dto.ReceiptWithStore;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
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
public class ReceiptListController {

	@EJB
	private ReceiptService receiptService;

	@EJB
	private StoreService storeService;

	private List<ReceiptWithStore> receiptList;

	public List<ReceiptWithStore> getReceiptList() {
		return receiptList;
	}

	@PostConstruct
	public void init() {
		List<ReceiptDto> receiptDtos = receiptService.listNewestReceipts();
		receiptList = new ArrayList<>();
		for (ReceiptDto receiptDto : receiptDtos) {
			ReceiptWithStore receiptWithStore = new ReceiptWithStore();
			receiptWithStore.setId(receiptDto.getId());
			receiptWithStore.setPurchaseDate(receiptDto.getPurchaseDate());
			receiptWithStore.setTotalAmount(receiptDto.getTotalAmount());
			receiptWithStore.setStore(storeService.findById(receiptDto.getStoreId()).getName());
			receiptList.add(receiptWithStore);
		}
	}

}
